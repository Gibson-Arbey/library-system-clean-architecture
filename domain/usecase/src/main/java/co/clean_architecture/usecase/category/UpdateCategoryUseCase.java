package co.clean_architecture.usecase.category;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.exception.CategoryNotFoundException;
import co.clean_architecture.model.category.exception.ExistsCategoryException;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import co.clean_architecture.usecase.category.command.UpdateCategoryCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Mono<Category> execute(UpdateCategoryCommand command) {

        return categoryRepository.findById(command.id())
                .switchIfEmpty(
                        Mono.error(new CategoryNotFoundException(
                                "Category not found with id: " + command.id()
                        ))
                )
                .flatMap(existingCategory -> {

                    // Si el nombre cambió, validamos que no exista otro igual
                    if (!existingCategory.getName().equals(command.name())) {
                        return categoryRepository.existsByName(command.name())
                            .flatMap(exists -> {
                                if (exists) {
                                    return Mono.error(
                                        new ExistsCategoryException(
                                            "Category with name '" + command.name() + "' already exists"
                                        )
                                    );
                                }

                                existingCategory.withUpdatedValues(command.name(), command.description());
                                return categoryRepository.save(existingCategory);
                            });
                    }

                    // Si el nombre no cambió, solo actualizamos
                    existingCategory.withUpdatedValues(command.name(), command.description());
                    return categoryRepository.save(existingCategory);
                });
    }
}
