package co.clean_architecture.api.book.response;

import co.clean_architecture.model.bookcopy.BookCopy;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookCopyResponse {

    private Long id;

    private Long bookId;

    private String barcode;

    private String status;

    private BookCopyResponse(Long id, Long bookId, String barcode, String status) {
        this.id = id;
        this.bookId = bookId;
        this.barcode = barcode;
        this.status = status;
    }

    public static BookCopyResponse fromDomain(BookCopy bookCopy) {
        return new BookCopyResponse(
            bookCopy.getId(),
            bookCopy.getBookId(),
            bookCopy.getBarcode(),
            bookCopy.getStatus().name());
    }

}
