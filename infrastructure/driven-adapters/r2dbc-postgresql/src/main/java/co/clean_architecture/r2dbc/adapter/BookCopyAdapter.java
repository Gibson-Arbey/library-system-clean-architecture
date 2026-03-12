package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.bookcopy.BookCopy;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.r2dbc.mapper.BookCopyMapper;
import co.clean_architecture.r2dbc.repository.BookCopyR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookCopyAdapter implements BookCopyRepository {

    private final BookCopyR2dbcRepository bookCopyR2dbcRepository;
    private final BookCopyMapper bookCopyMapper;

    @Override
    public Flux<BookCopy> saveAll(Flux<BookCopy> bookCopies) {
        return bookCopies
            .map(bookCopyMapper::toEntity)
            .as(bookCopyR2dbcRepository::saveAll)
            .map(bookCopyMapper::toDomain);
    }

    @Override
    public Flux<BookCopy> findAllByBookId(Long bookId) {
        return bookCopyR2dbcRepository
            .findAllByBookId(bookId)
            .map(bookCopyMapper::toDomain);
    }

    @Override
    public Flux<BookCopy> findAllByBookIdAndStatus(Long bookId, String status) {
        return bookCopyR2dbcRepository
            .findAllByBookIdAndStatus(bookId, status)
            .map(bookCopyMapper::toDomain);
    }

    @Override
    public Mono<BookCopy> findById(Long id) {
        return bookCopyR2dbcRepository
            .findById(id)
            .map(bookCopyMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsAllCopiesByStatusInAndBookId(List<String> status, Long bookId) {
        return bookCopyR2dbcRepository.existsAllCopiesByStatusInAndBookId(status, bookId);
    }

    @Override
    public Mono<Void> deleteAllByBookId(Long bookId) {
        return bookCopyR2dbcRepository
            .findAllByBookId(bookId)
            .flatMap(bookCopy -> bookCopyR2dbcRepository.deleteById(bookCopy.getId()))
            .then();
    }
}
