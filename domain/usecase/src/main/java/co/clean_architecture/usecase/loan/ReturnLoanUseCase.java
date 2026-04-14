package co.clean_architecture.usecase.loan;

import co.clean_architecture.model.bookcopy.StatusBookCopyEnum;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.model.loan.LoanStatus;
import co.clean_architecture.model.loan.exception.LoanIsReturnedException;
import co.clean_architecture.model.loan.exception.LoanNotFoundException;
import co.clean_architecture.model.loan.gateways.LoanRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
public class ReturnLoanUseCase {

    private final LoanRepository loanRepository;
    private final BookCopyRepository  bookCopyRepository;

    public Mono<Void> execute(Long loanId) {
        return loanRepository.findById(loanId)
            .switchIfEmpty(Mono.error(new LoanNotFoundException("The loan no exists")))
            .flatMap(loan -> {
                if (Objects.equals(loan.getStatus(), LoanStatus.RETURNED.name())) {
                    return Mono.error(new LoanIsReturnedException("The loan is already returned"));
                }

                return bookCopyRepository.updateStatusByBookCopyId(
                    loan.getBookCopyId(),
                    StatusBookCopyEnum.AVAILABLE.name()
                )
                .then(loanRepository.returnLoan(loanId, LocalDateTime.now()));
            });
    }

}
