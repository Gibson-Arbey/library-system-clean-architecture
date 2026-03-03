package co.clean_architecture.model.book.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class BookNotFoundException extends DomainException {
    public BookNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "BOOK_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
