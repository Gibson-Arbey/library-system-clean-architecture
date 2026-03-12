package co.clean_architecture.usecase.book.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class BookCopyNotAvaliableException extends DomainException {
    public BookCopyNotAvaliableException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "BOOK_COPY_NOT_AVALIABLE";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.BUSINESS_RULE;
    }
}
