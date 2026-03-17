package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class InvalidMailException extends DomainException {
    public InvalidMailException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_MAIL_EXCEPTION";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
