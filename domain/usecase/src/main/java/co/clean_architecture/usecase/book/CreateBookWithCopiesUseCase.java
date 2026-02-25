package co.clean_architecture.usecase.book;

import co.clean_architecture.model.book.Book;
import co.clean_architecture.model.book.gateways.BookRepository;
import co.clean_architecture.model.bookcopy.BookCopy;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.model.category.exception.CategoryNotFoundException;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import co.clean_architecture.usecase.book.command.CreateBookCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateBookWithCopiesUseCase {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final CategoryRepository categoryRepository;

    public Mono<Book> execute(CreateBookCommand command) {

        return categoryRepository.existsById(command.categoryId())
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(
                        new CategoryNotFoundException("Category with id '" + command.categoryId() + "' does not exist")
                    );
                }
                Book book = Book.create(
                    command.title(),
                    command.author(),
                    command.isbn(),
                    command.publicationYear(),
                    command.publisher(),
                    command.categoryId()
                );

                return bookRepository.save(book)
                    .flatMap(savedBook ->
                        Flux.fromIterable(command.copies())
                            .map(copyCommand ->
                                BookCopy.create(
                                    savedBook.getId(),
                                    copyCommand.barcode()
                                )
                            )
                        .as(bookCopyRepository::saveAll)
                        .then(Mono.just(savedBook))
                    );
                });

    }
}