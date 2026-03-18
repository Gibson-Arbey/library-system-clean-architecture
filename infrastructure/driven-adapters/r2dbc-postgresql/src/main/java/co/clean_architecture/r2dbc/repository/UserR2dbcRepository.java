package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity, Long> {

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE email = :email
        )
    """)
    Mono<Boolean> existsByEmail(String email);

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE user_username = :username
        )
    """)
    Mono<Boolean> existsByUsername(String username);
}
