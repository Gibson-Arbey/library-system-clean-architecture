package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.RoleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface RoleR2dbcRepository extends ReactiveCrudRepository<RoleEntity, Long> {

    @Query("SELECT * FROM roles WHERE role_name = :name")
    Mono<RoleEntity> findByName(@Param("name") String name);

    @Query("SELECT * FROM roles WHERE role_name IN (:names)")
    Flux<RoleEntity> findAllByNameIn(@Param("names") Set<String> names);
}
