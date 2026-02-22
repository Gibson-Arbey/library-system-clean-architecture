package co.clean_architecture.usecase.category;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Flux<Category> execute() {
        return categoryRepository.findAll();
    }
}
