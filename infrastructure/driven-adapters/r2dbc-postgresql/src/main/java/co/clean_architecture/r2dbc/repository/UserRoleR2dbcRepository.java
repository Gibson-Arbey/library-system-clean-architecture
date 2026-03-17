package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.UserRoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRoleR2dbcRepository extends ReactiveCrudRepository<UserRoleEntity, Long> {
}
