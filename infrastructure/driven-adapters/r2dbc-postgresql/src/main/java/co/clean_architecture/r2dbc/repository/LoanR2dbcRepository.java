package co.clean_architecture.r2dbc.repository;

import co.clean_architecture.r2dbc.entity.LoanEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoanR2dbcRepository extends ReactiveCrudRepository<LoanEntity, Long> {
}
