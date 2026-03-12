package co.clean_architecture.model.book.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BookCriteria {

    private String title;

    private String author;

    private String isbn;

    private Boolean applyFilterPublicationYear;
    private List<Integer> publicationYear;

    private String publisher;

    private Boolean applyFilterCategoryIds;
    private List<Long> categoryIds;

    private Integer page;

    private Integer pageSize;

    public void setValuesIfIsNull() {
        if (title == null) {
            title = "";
        }
        if (author == null) {
            author = "";
        }
        if (isbn == null) {
            isbn = "";
        }
        if (publicationYear == null || publicationYear.isEmpty()) {
            applyFilterPublicationYear = false;
            publicationYear = List.of(0);
        }
        if (publisher == null) {
            publisher = "";
        }
        if (categoryIds == null || categoryIds.isEmpty()) {
            applyFilterCategoryIds = false;
            categoryIds = List.of(0L);
        }
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

    }

    private BookCriteria(String title, String author, String isbn, List<Integer> publicationYear,
        String publisher, List<Long> categoryIds, Integer page, Integer pageSize) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.applyFilterPublicationYear = publicationYear != null && !publicationYear.isEmpty();
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.applyFilterCategoryIds = categoryIds != null && !categoryIds.isEmpty();
        this.categoryIds = categoryIds;
        this.page = page;
        this.pageSize = pageSize;
        this.setValuesIfIsNull();
    }

    public static BookCriteria of(String title, String author, String isbn, List<Integer> publicationYear,
        String publisher, List<Long> categoryIds, Integer page, Integer pageSize) {
        return new BookCriteria(title, author, isbn,
            publicationYear, publisher, categoryIds,
            page, pageSize);
    }
}
