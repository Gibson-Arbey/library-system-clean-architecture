package co.clean_architecture.r2dbc.mapper;

import co.clean_architecture.model.bookcopy.BookCopy;
import co.clean_architecture.model.bookcopy.StatusBookCopyEnum;
import co.clean_architecture.r2dbc.entity.BookCopyEntity;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

    public BookCopyEntity toEntity(BookCopy bookCopy) {
        if (bookCopy == null) {
            return null;
        }
        return BookCopyEntity.builder()
            .id(bookCopy.getId())
            .bookId(bookCopy.getBookId())
            .status(bookCopy.getStatus().name())
            .createdDate(bookCopy.getCreatedDate())
            .build();
    }

    public  BookCopy toEntity(BookCopyEntity bookCopyEntity) {
        if (bookCopyEntity == null) {
            return null;
        }
        return BookCopy.restore(
            bookCopyEntity.getId(),
            bookCopyEntity.getBookId(),
            bookCopyEntity.getBarcode(),
            StatusBookCopyEnum.valueOf(bookCopyEntity.getStatus()),
            bookCopyEntity.getCreatedDate()
        );
    }
}
