package co.clean_architecture.api.loan.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateLoanRequest {

    private Long userId;

    private Long bookCopyId;
}
