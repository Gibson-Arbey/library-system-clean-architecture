package co.clean_architecture.api.loan;

import co.clean_architecture.api.loan.mapper.CreateLoanRequestMapper;
import co.clean_architecture.api.loan.request.CreateLoanRequest;
import co.clean_architecture.api.loan.response.LoanResponse;
import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.usecase.loan.BorrowBookUseCase;
import co.clean_architecture.usecase.loan.GetAllByUserIdUseCase;
import co.clean_architecture.usecase.loan.GetByBookCopyIdAndStatusUseCase;
import co.clean_architecture.usecase.loan.ReturnLoanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoanHandler {

    private final BorrowBookUseCase  borrowBookUseCase;
    private final GetAllByUserIdUseCase getAllByUserIdUseCase;
    private final GetByBookCopyIdAndStatusUseCase getByBookCopyIdAndStatusUseCase;
    private final ReturnLoanUseCase returnLoanUseCase;

    // Mappers
    private final CreateLoanRequestMapper createLoanRequestMapper;

    public Mono<ServerResponse> createLoan(ServerRequest request) {
        return request.bodyToMono(CreateLoanRequest.class)
            .map(createLoanRequestMapper::toCommand)
            .flatMap(borrowBookUseCase::execute)
            .flatMap(loan -> toResponse(HttpStatus.CREATED, loan));
    }

    public Mono<ServerResponse> getAllLoansByUserId(ServerRequest request) {
        Long userId = Long.valueOf(request.pathVariable("userId"));
        return ServerResponse
            .status(HttpStatus.OK)
            .body(getAllByUserIdUseCase.execute(userId).map(LoanResponse::fromDomain), LoanResponse.class);
    }

    public Mono<ServerResponse> getLoanByBookCopyIdAndStatus(ServerRequest request) {
        Long bookCopyId = request.queryParam("bookCopyId")
                .map(Long::valueOf)
                .orElse(null);

        String status = request.queryParam("status")
                .map(String::valueOf)
                .orElse(null);

        return getByBookCopyIdAndStatusUseCase
            .execute(bookCopyId, status)
            .flatMap( loan -> toResponse(HttpStatus.OK, loan));
    }

    public Mono<ServerResponse> returnLoan(ServerRequest request) {
        Long loanId = Long.valueOf(request.pathVariable("loanId"));
        return returnLoanUseCase.execute(loanId)
            .then(ServerResponse.noContent().build());
    }

    private Mono<ServerResponse> toResponse(HttpStatus status, Loan loan) {
        return ServerResponse
                .status(status)
                .bodyValue(LoanResponse.fromDomain(loan));
    }
}
