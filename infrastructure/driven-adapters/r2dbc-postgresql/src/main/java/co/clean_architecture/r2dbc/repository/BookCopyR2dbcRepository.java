package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.BookCopyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookCopyR2dbcRepository extends ReactiveCrudRepository<BookCopyEntity, Long> {
}
