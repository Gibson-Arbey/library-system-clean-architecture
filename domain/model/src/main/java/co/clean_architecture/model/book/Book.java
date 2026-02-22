package co.clean_architecture.model.book;

import java.time.LocalDateTime;

public class Book {

    private Long id;

    private final String title;

    private final String author;

    private final String isbn;

    private final int publicationYear;

    private final String publisher;

    private final Long categoryId;

    private final LocalDateTime createdDate;

    private Book (Long id, String title, String author, String isbn, int publicationYear, String publisher, Long categoryId, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
    }

    public static Book create(String title, String author, String isbn, int publicationYear, String publisher, Long categoryId) {
        return new Book(null, title, author, isbn, publicationYear, publisher, categoryId, LocalDateTime.now());
    }

    public static Book restore(Long id, String title, String author, String isbn, int publicationYear, String publisher, Long categoryId, LocalDateTime createdDate) {
        return new Book(id, title, author, isbn, publicationYear, publisher, categoryId, createdDate);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
