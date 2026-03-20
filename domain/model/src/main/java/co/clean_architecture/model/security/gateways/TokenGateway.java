package co.clean_architecture.model.security.gateways;

public interface TokenGateway {

    String generateToken(Long userId, String username, String role);

    Long extractUserId(String token);

    String extractUsername(String token);

    String extractRole(String token);
}