package co.clean_architecture.api.loan;

import co.clean_architecture.api.loan.mapper.CreateLoanRequestMapper;
import co.clean_architecture.api.loan.request.CreateLoanRequest;
import co.clean_architecture.usecase.loan.BorrowBookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoanHandler {

    private final BorrowBookUseCase  borrowBookUseCase;

    // Mappers
    private final CreateLoanRequestMapper createLoanRequestMapper;

    public Mono<ServerResponse> createLoan(ServerRequest request) {
        return request.bodyToMono(CreateLoanRequest.class)
            .map(createLoanRequestMapper::toCommand)
            .flatMap(borrowBookUseCase::execute)
            .flatMap(loan -> ServerResponse.ok().bodyValue(loan));
    }
}
