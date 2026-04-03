package co.clean_architecture.api.loan;

import co.clean_architecture.usecase.loan.BorrowBookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanHandler {

    private final BorrowBookUseCase  borrowBookUseCase;
}
