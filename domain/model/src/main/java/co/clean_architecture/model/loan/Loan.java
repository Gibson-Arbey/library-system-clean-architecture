package co.clean_architecture.model.loan;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Loan {

    private Long id;
    private final Long userId;
    private final Long bookCopyId;
    private final LocalDateTime loanDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime returnDate;
    private final String status;
    private final LocalDateTime createdDate;

    private Loan(
            Long id,
            Long userId,
            Long bookCopyId,
            LocalDateTime loanDate,
            LocalDateTime dueDate,
            LocalDateTime returnDate,
            String status,
            LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.bookCopyId = bookCopyId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.createdDate = createdDate;
    }

    public static Loan create(
            Long userId,
            Long bookCopyId,
            LocalDateTime loanDate,
            LocalDateTime dueDate
    ) {
        return new Loan(
                null, userId, bookCopyId, loanDate, dueDate, null, LoanStatus.ACTIVE.name(), LocalDateTime.now()
        );
    }

    public static Loan restore(
            Long id,
            Long userId,
            Long bookCopyId,
            LocalDateTime loanDate,
            LocalDateTime dueDate,
            LocalDateTime returnDate,
            String status,
            LocalDateTime createdDate
    ) {
        return new Loan(
                id, userId, bookCopyId, loanDate, dueDate, returnDate, status, createdDate
        );
    }

}
