package co.clean_architecture.model.bookcopy.gateways.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class BookCopyNotFoundException extends DomainException {
    public BookCopyNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "BOOK_COPY_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
