package co.clean_architecture.model.category.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class CategoryNotFoundException extends DomainException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "CATEGORY_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
