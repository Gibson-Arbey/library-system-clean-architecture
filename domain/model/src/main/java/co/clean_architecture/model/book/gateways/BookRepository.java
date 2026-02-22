package co.clean_architecture.model.book.gateways;

import co.clean_architecture.model.book.Book;
import reactor.core.publisher.Mono;

public interface BookRepository {

    Mono<Book> save(Book book);
}
