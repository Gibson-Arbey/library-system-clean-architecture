package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.CategoryEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CategoryR2dbcRepository extends ReactiveCrudRepository<CategoryEntity, Long> {

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM categories
            WHERE cate_name = :name
        )
    """)
    Mono<Boolean> existsByName(@Param("name") String name);

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM books b
            WHERE b.cate_id = :id
        )
    """)
    Mono<Boolean> categoryInUse(@Param("id") Long id);
}
