package co.clean_architecture.security.handler;

import co.clean_architecture.security.exception.UserStatusException;
import co.clean_architecture.model.error.ErrorResponse;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Component
public class JwtAuthenticationHandler implements ServerAuthenticationEntryPoint {

    private final tools.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> commence(
            ServerWebExchange exchange,
            AuthenticationException ex
    ) {

        String code = "AUTHENTICATION_INVALID";

        if (ex instanceof UserStatusException userStatusEx) {
            code = userStatusEx.getCode();
        }

        co.clean_architecture.model.error.ErrorResponse error =
                co.clean_architecture.model.error.ErrorResponse.builder()
                        .code(code)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build();

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(error);
        } catch (Exception e) {
            bytes = new byte[0];
        }

        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(buffer));
    }
}