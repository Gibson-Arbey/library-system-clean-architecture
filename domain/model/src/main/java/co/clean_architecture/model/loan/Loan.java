package co.clean_architecture.model.loan;

import java.time.LocalDateTime;

public class Loan {

    private Long id;
    private final Long userId;
    private final Long bookCopyId;
    private final LocalDateTime loanDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime returnDate;
    private final String status;

    private Loan(
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

    public static Loan crate(
            Long userId,
            Long bookCopyId,
            LocalDateTime loanDate,
            LocalDateTime dueDate,
            String status
    ) {
        return new Loan(
                null, userId, bookCopyId, loanDate, dueDate, null, status
        );
    }

    public static Loan restore(
            Long id,
            Long userId,
            Long bookCopyId,
            LocalDateTime loanDate,
            LocalDateTime dueDate,
            LocalDateTime returnDate,
            String status
    ) {
        return new Loan(
                id, userId, bookCopyId, loanDate, dueDate, returnDate, status
        );
    }

}
