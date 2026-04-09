package co.clean_architecture.usecase.loan;

import co.clean_architecture.model.loan.gateways.LoanRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ExpireLoanUseCase {

    private final LoanRepository loanRepository;

    public Mono<Void> execute() {
        return loanRepository.expireLoans(LocalDateTime.now())
            .doOnNext(loan -> System.out.println("Expired loan: " + loan.getId()))
            .then();
    }
}
