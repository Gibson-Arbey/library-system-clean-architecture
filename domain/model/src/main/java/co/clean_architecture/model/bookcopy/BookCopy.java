package co.clean_architecture.model.bookcopy;

import java.time.LocalDateTime;

public class BookCopy {

    private Long id;

    private final Long bookId;

    private final String barcode;

    private final StatusBookCopyEnum status;

    private final LocalDateTime createdDate;

    private BookCopy(Long id, Long bookId, String barcode, StatusBookCopyEnum status, LocalDateTime createdDate) {
        this.id = id;
        this.bookId = bookId;
        this.barcode = barcode;
        this.status = status;
        this.createdDate = createdDate;
    }

    public static BookCopy create(Long bookId, String barcode) {
        return new BookCopy(null, bookId, barcode, StatusBookCopyEnum.AVALIABLE, LocalDateTime.now());
    }

    public static BookCopy restore(Long id, Long bookId, String barcode, StatusBookCopyEnum status, LocalDateTime createdDate) {
        return new BookCopy(id, bookId, barcode, status, createdDate);
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBarcode() {
        return barcode;
    }

    public StatusBookCopyEnum getStatus() {
        return status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
