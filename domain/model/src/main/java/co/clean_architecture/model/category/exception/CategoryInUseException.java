package co.clean_architecture.model.category.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class CategoryInUseException extends DomainException {
    public CategoryInUseException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "CATEGORY_IN_USE";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
