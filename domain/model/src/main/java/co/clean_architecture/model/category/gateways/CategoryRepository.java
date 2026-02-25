package co.clean_architecture.model.category.gateways;

import co.clean_architecture.model.category.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository {

    Mono<Category> save(Category category);

    Flux<Category> findAll();

    Mono<Category> findById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<Boolean> existsByName(String name);

    Mono<Boolean> categoryInUse(Long id);

    Mono<Boolean> existsById(Long id);

}
