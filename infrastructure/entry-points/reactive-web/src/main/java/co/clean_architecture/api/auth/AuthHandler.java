package co.clean_architecture.api.auth;

import co.clean_architecture.api.auth.mapper.AuthResponseMapper;
import co.clean_architecture.api.auth.request.AuthRequest;
import co.clean_architecture.api.book.request.CreateBookRequest;
import co.clean_architecture.usecase.auth.AuthenticateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    // mappers
    private final AuthResponseMapper authResponseMapper;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(AuthRequest.class)
                // 1. ejecutar use case
                .flatMap(req ->
                        authenticateUserUseCase.login(
                                req.getUsername(),
                                req.getPassword()
                        )
                )
                // 2. mapear respuesta
                .map(authResponseMapper::toResponse)
                // 3. construir response HTTP
                .flatMap(response ->
                        ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response)
                );
    }

}
