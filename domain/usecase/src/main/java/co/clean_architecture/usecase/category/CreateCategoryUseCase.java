package co.clean_architecture.usecase.category;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.exception.ExistsCategoryException;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import co.clean_architecture.usecase.category.command.CreateCategoryCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Mono<Category> execute(CreateCategoryCommand command) {
        return categoryRepository.existsByName(command.name())
            .flatMap(exists -> {
                if (exists) {
                    return Mono.error(
                            new ExistsCategoryException("Category with name '" + command.name() + "' already exists")
                    );
                }

                Category category = Category.create(command.name(), command.description());
                return categoryRepository.save(category);
            });
    }
}
