package co.clean_architecture.api.loan.mapper;

import co.clean_architecture.api.loan.request.CreateLoanRequest;
import co.clean_architecture.usecase.loan.command.CreateLoanCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateLoanRequestMapper {


    public CreateLoanCommand toCommand(CreateLoanRequest request) {
        return new CreateLoanCommand(request.getUserId(), request.getBookCopyId());
    }
}
