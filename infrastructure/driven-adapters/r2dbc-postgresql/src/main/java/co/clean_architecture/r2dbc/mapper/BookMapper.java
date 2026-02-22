package co.clean_architecture.r2dbc.mapper;

import co.clean_architecture.model.book.Book;
import co.clean_architecture.r2dbc.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookEntity toEntity(Book book) {
        if (book == null) {
            return null;
        }
        return BookEntity.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .categoryId(book.getCategoryId())
            .createdDate(book.getCreatedDate())
            .build();
    }
}
