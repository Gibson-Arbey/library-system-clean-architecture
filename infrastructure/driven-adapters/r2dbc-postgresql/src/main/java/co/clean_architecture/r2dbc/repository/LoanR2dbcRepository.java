package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.LoanEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoanR2dbcRepository extends ReactiveCrudRepository<LoanEntity, Long> {

    @Query("""
        SELECT * FROM loans WHERE user_id = :userId
    """)
    Flux<LoanEntity> findAllByUserId(
            @Param("userId") Long userId
    );

    @Query("""
        SELECT * FROM loans WHERE boco_id = :bookCopyId AND loan_status = :status
    """)
    Mono<LoanEntity> findByBookCopyIdAndStatus(
            @Param("bookCopyId") Long bookCopyId,
            @Param("status") String status
    );
}
