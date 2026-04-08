package co.clean_architecture.api.loan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LoanRouter {
    @Bean
    public RouterFunction<ServerResponse> loanRoutes(LoanHandler loanHandler) {
        return RouterFunctions.route()
            .POST("/api/v1/loan", loanHandler::createLoan)
            .build();
    }

}
