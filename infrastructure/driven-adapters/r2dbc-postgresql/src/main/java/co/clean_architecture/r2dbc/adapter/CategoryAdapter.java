package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import co.clean_architecture.r2dbc.mapper.CategoryMapper;
import co.clean_architecture.r2dbc.repository.CategoryR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryRepository {

    private final CategoryR2dbcRepository categoryR2dbcRepository;
    private final CategoryMapper categoryMapper;


    @Override
    @Transactional
    public Mono<Category> save(Category category) {
        return categoryR2dbcRepository
            .save(categoryMapper.toEntity(category))
            .map(categoryMapper::toDomain);
    }

    @Override
    public Flux<Category> findAll() {
        return categoryR2dbcRepository
            .findAll()
            .map(categoryMapper::toDomain);
    }

    @Override
    public Mono<Category> findById(Long id) {
        return categoryR2dbcRepository
            .findById(id)
            .map(categoryMapper::toDomain);
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(Long id) {
        return categoryR2dbcRepository
            .deleteById(id)
            .then();
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return categoryR2dbcRepository.existsByName(name);
    }

    @Override
    public Mono<Boolean> categoryInUse(Long id) {
        return categoryR2dbcRepository.categoryInUse(id);
    }
}
