package co.clean_architecture.model.loan.gateways;

import co.clean_architecture.model.loan.Loan;
import reactor.core.publisher.Mono;

public interface LoanRepository {

    Mono<Loan> save(Loan loan);
}
