package co.clean_architecture.usecase.loan;

import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.model.bookcopy.gateways.exception.BookCopyNotFoundException;
import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.model.loan.exception.LoanNotFoundException;
import co.clean_architecture.model.loan.gateways.LoanRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetByBookCopyIdAndStatusUseCase {

    private final BookCopyRepository bookCopyRepository;
    private final LoanRepository loanRepository;

    public Mono<Loan> execute(Long bookCopyId, String status) {
        return bookCopyRepository.findById(bookCopyId)
            .switchIfEmpty(Mono.error(new BookCopyNotFoundException("Book copy not found")))
            .flatMap(bookCopy -> loanRepository.getByBookCopyIdAndStatus(bookCopyId, status)
                .switchIfEmpty(Mono.error(new LoanNotFoundException("Loan not found for the given book copy id and status"))));
    }
}
