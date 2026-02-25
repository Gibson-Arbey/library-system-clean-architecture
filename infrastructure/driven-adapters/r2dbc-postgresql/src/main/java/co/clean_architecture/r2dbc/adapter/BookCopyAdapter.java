package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.bookcopy.BookCopy;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.r2dbc.mapper.BookCopyMapper;
import co.clean_architecture.r2dbc.repository.BookCopyR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BookCopyAdapter implements BookCopyRepository {

    private final BookCopyR2dbcRepository bookCopyR2dbcRepository;
    private final BookCopyMapper bookCopyMapper;

    @Override
    public Flux<BookCopy> saveAll(Flux<BookCopy> bookCopies) {
        return null;
    }

    @Override
    public Flux<BookCopy> findAllByBookId(Long bookId) {
        return null;
    }

    @Override
    public Flux<BookCopy> findAllByBookIdAndAvailableTrue(Long bookId) {
        return null;
    }

    @Override
    public Mono<BookCopy> findById(Long id) {
        return null;
    }
}
