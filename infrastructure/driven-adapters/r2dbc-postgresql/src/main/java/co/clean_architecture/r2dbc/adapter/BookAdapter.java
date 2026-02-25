package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.book.Book;
import co.clean_architecture.model.book.criteria.BookCriteria;
import co.clean_architecture.model.book.gateways.BookRepository;
import co.clean_architecture.r2dbc.mapper.BookMapper;
import co.clean_architecture.r2dbc.repository.BookR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BookAdapter implements BookRepository {

    private final BookMapper bookMapper;
    private final BookR2dbcRepository bookR2dbcRepository;

    @Override
    @Transactional
    public Mono<Book> save(Book book) {
        return bookR2dbcRepository
            .save(bookMapper.toEntity(book))
            .map(bookMapper::toDomain);
    }

    @Override
    public Flux<Book> findAllByCriteria(BookCriteria criteria) {
        return null;
    }

    @Override
    public Mono<Book> findById(Long id) {
        return bookR2dbcRepository
            .findById(id)
            .map(bookMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return bookR2dbcRepository
            .deleteById(id)
            .then();
    }
}
