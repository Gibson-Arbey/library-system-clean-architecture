package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookR2dbcRepository extends ReactiveCrudRepository<BookEntity, Long> {
}
