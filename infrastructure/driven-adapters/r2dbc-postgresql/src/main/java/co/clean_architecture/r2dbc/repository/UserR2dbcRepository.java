package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity, Long> {

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE user_mail = :mail
        )
    """)
    Mono<Boolean> existsByMail(String mail);

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
            WHERE user_id = :id AND user_status = 'ACTIVE'
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

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE user_id != :id AND user_username = :username
        )
    """)
    Mono<Boolean> usernameHasOccupied(
        @Param("id") Long id,
        @Param("username") String username
    );

    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM users
            WHERE user_id != :id AND user_mail = :mail
        )
    """)
    Mono<Boolean> mailHasOccupied(
        @Param("id") Long id,
        @Param("mail") String mail
    );

    @Query("""
        SELECT *
        FROM users
        WHERE 
            (:username = '' OR  user_username ILIKE CONCAT('%', :username, '%')) AND 
            (:mail = '' OR user_mail ILIKE CONCAT('%', :mail, '%')) AND 
            (:applyFilterStatus = FALSE OR user_status IN (:statuses)) AND 
            (:applyFilterRole = FALSE OR role_id IN (:roleIds))
        ORDER BY user_id
        LIMIT :limit OFFSET :offset
    """)
    Flux<UserEntity> findAllByFilters(
        @Param("username") String username,
        @Param("mail") String mail,
        @Param("applyFilterStatus") Boolean applyFilterStatus,
        @Param("statuses") List<String> statuses,
        @Param("applyFilterRole") Boolean applyFilterRole,
        @Param("roleIds") List<Long> roleIds,
        @Param("limit") int limit,
        @Param("offset") int offset
    );
}
