package co.clean_architecture.api.auth.mapper;

import co.clean_architecture.api.auth.response.AuthResponse;
import co.clean_architecture.api.auth.response.AuthUserResponse;
import co.clean_architecture.model.security.AuthResult;
import org.springframework.stereotype.Component;

@Component
public class AuthResponseMapper {

    public AuthResponse toResponse(AuthResult authResult) {
        return AuthResponse.builder()
                .token(authResult.getToken())
                .user(
                        AuthUserResponse.builder()
                                .id(authResult.getUserId())
                                .username(authResult.getUsername())
                                .email(authResult.getEmail())
                                .role(authResult.getRole())
                                .build()
                )
                .build();
    }
}
