package co.clean_architecture.api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthRouter {

    @Bean
    public RouterFunction<ServerResponse> authRoutes(AuthHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/auth/login", handler::login)
                .build();

    }
}
