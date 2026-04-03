package co.clean_architecture.r2dbc.mapper;

import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.r2dbc.entity.LoanEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanEntity toEntity(Loan loan) {
        if (loan == null) {
            return null;
        }
        return LoanEntity.builder()
            .id(loan.getId())
            .userId(loan.getUserId())
            .bookCopyId(loan.getBookCopyId())
            .loanDate(loan.getLoanDate())
            .dueDate(loan.getDueDate())
            .returnDate(loan.getReturnDate())
            .status(loan.getStatus())
            .createdDate(loan.getCreatedDate())
            .build();
    }

    public Loan toDomain(LoanEntity loan) {
        if (loan == null) {
            return null;
        }
        return Loan.restore(
            loan.getId(),
            loan.getUserId(),
            loan.getBookCopyId(),
            loan.getLoanDate(),
            loan.getDueDate(),
            loan.getReturnDate(),
            loan.getStatus(),
            loan.getCreatedDate()
        );
    }
}
