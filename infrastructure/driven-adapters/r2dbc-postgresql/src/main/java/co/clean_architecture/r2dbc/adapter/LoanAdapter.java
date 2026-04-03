package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.model.loan.gateways.LoanRepository;
import co.clean_architecture.r2dbc.mapper.LoanMapper;
import co.clean_architecture.r2dbc.repository.LoanR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class LoanAdapter implements LoanRepository {

    private final LoanR2dbcRepository loanR2dbcRepository;
    private final LoanMapper loanMapper;

    @Override
    @Transactional
    public Mono<Loan> save(Loan loan) {
        return loanR2dbcRepository
            .save(loanMapper.toEntity(loan))
            .map(loanMapper::toDomain);
    }
}
