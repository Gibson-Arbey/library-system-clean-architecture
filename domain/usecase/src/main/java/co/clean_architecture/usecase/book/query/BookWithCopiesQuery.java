package co.clean_architecture.usecase.book.query;

import co.clean_architecture.model.book.Book;
import co.clean_architecture.model.bookcopy.BookCopy;

import java.util.List;

public record BookWithCopiesQuery(
        Book book,
        List<BookCopy> bookCopies
) {
}
