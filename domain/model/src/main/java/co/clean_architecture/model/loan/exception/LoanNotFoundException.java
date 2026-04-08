package co.clean_architecture.model.loan.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class LoanNotFoundException extends DomainException {

    public LoanNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "LOAN_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
