package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.LoanEntity;
import org.springframework.data.r2dbc.repository.Modifying;
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

    @Query("""
        UPDATE loans SET loan_status = 'EXPIRED'
        WHERE loan_duedate < :dateTime AND loan_status = 'OVERDUE' RETURNING *;
    """)
    Flux<LoanEntity> expireLoans(
            @Param("dateTime") String dateTime
    );

    @Query("""
        UPDATE loans SET loan_status = 'RETURNED', loan_returndate = CURRENT_TIMESTAMP
        WHERE loan_id = :loanId;
    """)
    Mono<LoanEntity> returnedLoan(
            @Param("loanId") Long loanId
    );
}
