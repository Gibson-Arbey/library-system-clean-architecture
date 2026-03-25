package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity, Long> {

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE user_mail = :email
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

    @Query("""
        SELECT *
        FROM users
        WHERE user_username = :username
        LIMIT 1
    """)
    Mono<UserEntity> findByUsername(@Param("username") String username);

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE id = :id AND user_status = 'ACTIVE'
        )
    """)
    Mono<Boolean> userStatusIsActive(@Param("id") Long id);

    @Query("""
        UPDATE users
        SET user_status = :status
        WHERE user_id = :id
    """)
    Mono<Void> updateUserStatus(
        @Param("id") Long id,
        @Param("status") String status
    );
}
