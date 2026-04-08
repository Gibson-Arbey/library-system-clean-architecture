package co.clean_architecture.api.loan.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class LoanResponse {

    private Long id;
    private Long userId;
    private Long bookCopyId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private String status;

    private LoanResponse(
            Long id,
            Long userId,
            Long bookCopyId,
            LocalDateTime loanDate,
            LocalDateTime dueDate,
            LocalDateTime returnDate,
            String status) {
        this.id = id;
        this.userId = userId;
        this.bookCopyId = bookCopyId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public static LoanResponse fromDomain(co.clean_architecture.model.loan.Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getUserId(),
                loan.getBookCopyId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus()
        );
    }
}
