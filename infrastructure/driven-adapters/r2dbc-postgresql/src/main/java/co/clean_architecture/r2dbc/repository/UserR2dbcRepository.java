package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity, Long> {
}
