package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.BookCopyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookCopyR2dbcRepository extends ReactiveCrudRepository<BookCopyEntity, Long> {

    @Query("SELECT * FROM bookcopies WHERE book_id = :bookId")
    Flux<BookCopyEntity> findAllByBookId(@Param("bookId") Long bookId);

    @Query("SELECT * FROM bookcopies WHERE book_id = :bookId AND status = :status")
    Flux<BookCopyEntity> findAllByBookIdAndStatus(
            @Param("bookId") Long bookId,
            @Param("status") String status
    );

    @Query("""
        SELECT NOT EXISTS (
                SELECT 1
                FROM bookcopies
                WHERE book_id = :bookId
                AND boco_status NOT IN (:status)
            )
    """)
    Mono<Boolean> existsAllCopiesByStatusInAndBookId(
            @Param("status") List<String> status,
            @Param("bookId") Long bookId);
}
