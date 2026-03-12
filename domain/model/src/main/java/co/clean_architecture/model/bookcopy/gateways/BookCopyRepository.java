package co.clean_architecture.model.bookcopy.gateways;

import co.clean_architecture.model.bookcopy.BookCopy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookCopyRepository {

    Flux<BookCopy> saveAll(Flux<BookCopy> bookCopies);

    Flux<BookCopy> findAllByBookId(Long bookId);

    Flux<BookCopy> findAllByBookIdAndStatus(Long bookId, String status);

    Mono<BookCopy> findById(Long id);

    Mono<Boolean> existsAllCopiesByStatusInAndBookId(List<String> status, Long bookId);

    Mono<Void> deleteAllByBookId(Long bookId);
}
