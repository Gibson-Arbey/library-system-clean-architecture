package co.clean_architecture.usecase.book.query;

import co.clean_architecture.model.book.gateways.BookRepository;
import co.clean_architecture.model.bookcopy.StatusBookCopyEnum;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.usecase.book.exception.BookCopyNotAvaliableException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class DeleteBookWithCopiesUseCase {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository  bookRepository;


    public Mono<Void> execute(Long bookId) {
        List<String> status = List.of(
                StatusBookCopyEnum.BORROWED.name(),
                StatusBookCopyEnum.RESERVED.name()
        );

        return bookCopyRepository.existsAllCopiesByStatusInAndBookId(status, bookId)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new BookCopyNotAvaliableException(
                                        "Cannot delete book with id '" + bookId +
                                                "' because it has copies that are currently borrowed or reserved"
                                )
                        );
                    }

                    return bookCopyRepository.deleteAllByBookId(bookId)
                            .then(bookRepository.deleteById(bookId));
                });
    }

}
