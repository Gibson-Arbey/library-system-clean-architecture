package co.clean_architecture.r2dbc.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Table("books")
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @Column("book_id")
    private Long id;

    @Column("book_title")
    private String title;

    @Column("book_author")
    private String author;

    @Column("book_isbn")
    private String isbn;

    @Column("book_publicationyear")
    private int publicationYear;

    @Column("book_publisher")
    private String publisher;

    @Column("cate_id")
    private Long categoryId;

    @Column("book_createddate")
    private LocalDateTime createdDate;
}
