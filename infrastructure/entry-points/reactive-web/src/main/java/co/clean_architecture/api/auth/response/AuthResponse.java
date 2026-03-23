package co.clean_architecture.api.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class AuthResponse {

    private final String token;

    private final AuthUserResponse user;
}
