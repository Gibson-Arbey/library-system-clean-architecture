package co.clean_architecture.api.user;

import co.clean_architecture.api.user.mapper.CreateUserRequestMapper;
import co.clean_architecture.api.user.request.CreateUserRequest;
import co.clean_architecture.api.user.response.UserResponse;
import co.clean_architecture.model.user.User;
import co.clean_architecture.usecase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final CreateUserUseCase createUserUseCase;

    // mappers
    private final CreateUserRequestMapper createUserRequestMapper;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserRequest.class)
                .map(createUserRequestMapper::toCreateUserCommand)
                .flatMap(createUserUseCase::execute)
                .flatMap(user -> toResponse(user, HttpStatus.CREATED));
    }

    private Mono<ServerResponse> toResponse(User user, HttpStatus status) {
        return ServerResponse
                .status(status)
                .bodyValue(UserResponse.fromDomain(user));
    }
}
