package co.clean_architecture.usecase.category;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCategoryByIdUseCase {

    private final CategoryRepository categoryRepository;

    public Mono<Category> execute(Long id) {
        return categoryRepository.findById(id);
    }

}
