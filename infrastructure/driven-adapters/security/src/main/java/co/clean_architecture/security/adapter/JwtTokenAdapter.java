package co.clean_architecture.security.adapter;

import co.clean_architecture.model.security.gateways.TokenGateway;
import co.clean_architecture.security.exception.JwtAuthenticationException;
import co.clean_architecture.security.util.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtTokenAdapter implements TokenGateway {

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(Long userId, String username, String role) {
        return jwtUtil.createToken(username, userId, role);
    }

    @Override
    public Long extractUserId(String token) {
        return jwtUtil.extractUserId(decode(token));
    }

    @Override
    public String extractUsername(String token) {
        return jwtUtil.extractUsername(decode(token));
    }

    @Override
    public String extractRole(String token) {
        return jwtUtil.extractRole(decode(token));
    }

    private DecodedJWT decode(String token) {
        try {
            return jwtUtil.validateToken(token);
        } catch (Exception ex) {
            throw new JwtAuthenticationException(
                    ex.getMessage()
            );
        }
    }
}