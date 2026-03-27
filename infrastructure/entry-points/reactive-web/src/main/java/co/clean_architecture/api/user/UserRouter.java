package co.clean_architecture.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {


    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return RouterFunctions.route()
                .POST("/api/v1/user", userHandler::createUser)
                .GET("/api/v1/user/{id}", userHandler::getUserById)
                .PATCH("/api/v1/user/status", userHandler::updateUserStatus)
                .PUT("/api/v1/user/{id}", userHandler::updateUser)
                .build();
    }
}
