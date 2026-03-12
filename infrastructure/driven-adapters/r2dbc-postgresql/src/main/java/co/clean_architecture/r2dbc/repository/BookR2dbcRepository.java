package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.BookEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface BookR2dbcRepository extends ReactiveCrudRepository<BookEntity, Long> {

    @Query("""
        SELECT *
        FROM books
        WHERE (:title = '' OR LOWER(book_title) LIKE LOWER(CONCAT('%', :title, '%')))
        AND (:author = '' OR LOWER(book_author) LIKE LOWER(CONCAT('%', :author, '%')))
        AND (:isbn = '' OR LOWER(book_isbn) LIKE LOWER(CONCAT('%', :isbn, '%')))
        AND (:publisher = '' OR LOWER(book_publisher) LIKE LOWER(CONCAT('%', :publisher, '%')))
        AND (:applyFilterPublicationYear = false OR book_publicationyear IN (:publicationYears))
        AND (:applyFilterCategoryIds = false OR cate_id IN (:categoryIds))
        LIMIT :pageSize OFFSET :offset
    """)
    Flux<BookEntity> findAllByFilters(
        @Param("title") String title,
        @Param("author") String author,
        @Param("isbn") String isbn,
        @Param("publisher") String publisher,
        @Param("applyFilterPublicationYear") boolean applyFilterPublicationYear,
        @Param("publicationYears") List<Integer> publicationYears,
        @Param("applyFilterCategoryIds") boolean applyFilterCategoryIds,
        @Param("categoryIds") List<Long> categoryIds,
        @Param("pageSize") int pageSize,
        @Param("offset") int offset
    );
}
