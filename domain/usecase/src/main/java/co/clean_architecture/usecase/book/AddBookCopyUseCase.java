package co.clean_architecture.usecase.book;

import co.clean_architecture.model.book.exception.BookNotFoundException;
import co.clean_architecture.model.book.gateways.BookRepository;
import co.clean_architecture.model.bookcopy.BookCopy;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.usecase.book.command.CreateBookCopyCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class AddBookCopyUseCase {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public Flux<BookCopy> execute(Long bookId, List<CreateBookCopyCommand> commands) {

        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(
                        new BookNotFoundException(
                                "Book with id '" + bookId + "' not found"
                        )
                ))
                .flatMapMany(book ->
                        bookCopyRepository.saveAll(
                                Flux.fromIterable(commands)
                                        .map(copyCommand ->
                                                BookCopy.create(
                                                        book.getId(),
                                                        copyCommand.barcode()
                                                )
                                        )
                        )
                );
    }
}
