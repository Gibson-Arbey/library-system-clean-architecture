package co.clean_architecture.model.role.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class RoleNotFoundException extends DomainException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "ROLE_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
