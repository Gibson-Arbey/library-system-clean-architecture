package co.clean_architecture.model.book;

import co.clean_architecture.model.exception.InvalidFieldException;

import java.time.LocalDateTime;

public class Book {

    private Long id;

    private final String title;

    private final String author;

    private final String isbn;

    private final int publicationYear;

    private final String publisher;

    private final Long categoryId;

    private final Integer maxLoanDays;

    private final LocalDateTime createdDate;

    private Book (Long id, String title, String author, String isbn, int publicationYear, String publisher, Long categoryId, Integer maxLoanDays, LocalDateTime createdDate) {

        if (title == null || title.isBlank()) {
            throw new InvalidFieldException("Title cannot be null or blank");
        }

        if (author == null || author.isBlank()) {
            throw new InvalidFieldException("Author cannot be null or blank");
        }

        if (isbn == null || isbn.isBlank()) {
            throw new InvalidFieldException("ISBN cannot be null or blank");
        }

        if(publicationYear <= 0) {
            throw new InvalidFieldException("Publication year must be greater than 0");
        }

        if (publisher == null || publisher.isBlank()) {
            throw new InvalidFieldException("Publisher cannot be null or blank");
        }

        if(maxLoanDays <= 0) {
            throw new InvalidFieldException("Max loan days must be greater than 0");
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.categoryId = categoryId;
        this.maxLoanDays = maxLoanDays;
        this.createdDate = createdDate;
    }

    public static Book create(String title, String author, String isbn, int publicationYear, String publisher, Long categoryId, Integer maxLoanDays) {
        return new Book(null, title, author, isbn, publicationYear, publisher, categoryId, maxLoanDays, LocalDateTime.now());
    }

    public static Book restore(Long id, String title, String author, String isbn, int publicationYear, String publisher, Long categoryId, Integer maxLoanDays, LocalDateTime createdDate) {
        return new Book(id, title, author, isbn, publicationYear, publisher, categoryId, maxLoanDays, createdDate);
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

    public Integer getMaxLoanDays() { return maxLoanDays; }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
