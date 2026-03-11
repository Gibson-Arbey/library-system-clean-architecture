package co.clean_architecture.api.book.response;

import co.clean_architecture.usecase.book.query.BookWithCopiesQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BookWithCopiesResponse {

    private BookResponse book;
    private List<BookCopyResponse> copies;

    private BookWithCopiesResponse(BookResponse book, List<BookCopyResponse> copies) {
        this.book = book;
        this.copies = copies;
    }

    public static BookWithCopiesResponse fromDomain(BookWithCopiesQuery query) {
        return new BookWithCopiesResponse(
            BookResponse.fromDomain(query.book()),
            query.bookCopies().stream().map(BookCopyResponse::fromDomain).toList());
    }
}
