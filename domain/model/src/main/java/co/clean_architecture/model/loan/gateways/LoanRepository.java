package co.clean_architecture.model.loan.gateways;

import co.clean_architecture.model.loan.Loan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


public interface LoanRepository {

    Mono<Loan> save(Loan loan);

    Flux<Loan> getAllByUserId(Long userId);

    Mono<Loan> getByBookCopyIdAndStatus(Long bookCopyId, String status);

    Flux<Loan> expireLoans(LocalDateTime dateTime);


}
