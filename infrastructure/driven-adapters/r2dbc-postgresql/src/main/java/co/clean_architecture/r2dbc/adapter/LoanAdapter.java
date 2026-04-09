package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.model.loan.gateways.LoanRepository;
import co.clean_architecture.r2dbc.mapper.LoanMapper;
import co.clean_architecture.r2dbc.repository.LoanR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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

    @Override
    public Flux<Loan> getAllByUserId(Long userId) {
        return loanR2dbcRepository.findAllByUserId(userId).map(loanMapper::toDomain);
    }

    @Override
    public Mono<Loan> getByBookCopyIdAndStatus(Long bookCopyId, String status) {
        return loanR2dbcRepository.findByBookCopyIdAndStatus(bookCopyId, status).map(loanMapper::toDomain);
    }

    @Override
    @Transactional
    public Flux<Loan> expireLoans(LocalDateTime dateTime) {
        return loanR2dbcRepository.expireLoans(dateTime.toString()).map(loanMapper::toDomain);
    }

}
