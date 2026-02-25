package co.clean_architecture.model.bookcopy.gateways;

import co.clean_architecture.model.bookcopy.BookCopy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookCopyRepository {

    Flux<BookCopy> saveAll(Flux<BookCopy> bookCopies);

    Flux<BookCopy> findAllByBookId(Long bookId);

    Flux<BookCopy> findAllByBookIdAndAvailableTrue(Long bookId);

    Mono<BookCopy> findById(Long id);
}
