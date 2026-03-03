package co.clean_architecture.api.book.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateBookRequest {

    String title;
    String author;
    String isbn;
    int publicationYear;
    String publisher;
    Long categoryId;
    List<CreateBookCopyRequest> copies;
}
