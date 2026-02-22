package co.clean_architecture.usecase.category;

import co.clean_architecture.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Mono<Void> execute(Long id) {
        return categoryRepository.categoryInUse(id)
            .filter(inUse -> !inUse)
            .switchIfEmpty(
                Mono.error(new IllegalStateException(
                    "Cannot delete category with id " + id + " because it is in use"
                ))
            )
            .flatMap(ignored -> categoryRepository.deleteById(id));
    }
}
