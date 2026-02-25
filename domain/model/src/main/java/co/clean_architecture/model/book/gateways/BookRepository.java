package co.clean_architecture.model.book.gateways;

import co.clean_architecture.model.book.Book;
import co.clean_architecture.model.book.criteria.BookCriteria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {

    Mono<Book> save(Book book);

    Flux<Book> findAllByCriteria(BookCriteria criteria);

    Mono<Book> findById(Long id);

    Mono<Void> deleteById(Long id);
}
