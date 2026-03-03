package co.clean_architecture.api.book.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private int publicationYear;

    private String publisher;

    private Long categoryId;

    private BookResponse(Long id, String title, String author, String isbn, int publicationYear, String publisher, Long categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.categoryId = categoryId;
    }

     public static BookResponse fromDomain(co.clean_architecture.model.book.Book book) {
         return new BookResponse(
             book.getId(),
             book.getTitle(),
             book.getAuthor(),
             book.getIsbn(),
             book.getPublicationYear(),
             book.getPublisher(),
             book.getCategoryId());
     }
}
