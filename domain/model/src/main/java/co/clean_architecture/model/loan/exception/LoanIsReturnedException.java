package co.clean_architecture.model.loan.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class LoanIsReturnedException extends DomainException {
    public LoanIsReturnedException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "LOAN_IS_RETURNED";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.BUSINESS_RULE;
    }
}
