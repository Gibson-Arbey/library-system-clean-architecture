package co.clean_architecture.model.category.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class ExistsCategoryException extends DomainException {
    public ExistsCategoryException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "EXISTS_CATEGORY";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.BUSINESS_RULE;
    }
}
