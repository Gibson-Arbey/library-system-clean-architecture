package co.clean_architecture.scheduler;

import co.clean_architecture.usecase.loan.ExpireLoanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpireLoansScheduler {

    private final ExpireLoanUseCase expireLoanUseCase;

    @Scheduled(cron = "0 0 0 * * *")
    public void execute() {
        expireLoanUseCase.execute()
                .doOnError(e -> System.err.println("Error expiring loans: " + e.getMessage()))
                .subscribe();
    }
}
