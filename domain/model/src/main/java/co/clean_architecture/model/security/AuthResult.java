package co.clean_architecture.model.security;

import lombok.Getter;

@Getter
public class AuthResult {

    private final Long userId;
    private final String username;
    private final String email;
    private final String role;
    private final String token;

    private AuthResult(Long userId, String username, String email, String role, String token) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public static AuthResult of(Long userId, String username, String email, String role, String token) {
        return new AuthResult(userId, username, email, role, token);
    }

    //
}