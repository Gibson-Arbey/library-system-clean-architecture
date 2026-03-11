package co.clean_architecture.usecase.book;

import co.clean_architecture.model.book.criteria.BookCriteria;
import co.clean_architecture.model.book.gateways.BookRepository;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.usecase.book.query.BookWithCopiesQuery;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllBookWithCopiesUseCase {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public Flux<BookWithCopiesQuery> execute(BookCriteria criteria) {
        return bookRepository.findAllByCriteria(criteria)
            .flatMap(book ->
                bookCopyRepository.findAllByBookId(book.getId())
                    .collectList()
                    .map(copies -> new BookWithCopiesQuery(
                        book,
                        copies
                    ))
            );
    }
}
