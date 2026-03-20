package co.clean_architecture.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final SecurityConstant securityConstant;
    private final Algorithm algorithm;

    public JwtUtil(SecurityConstant securityConstant) {
        this.securityConstant = securityConstant;
        this.algorithm = Algorithm.HMAC256(securityConstant.getJwtKeyPrivate());
    }

    public String createToken(String username, Long userId, String roles) {
        return JWT.create()
                .withIssuer(securityConstant.getJwtUserGenerator())
                .withSubject(username)
                .withClaim("userId", userId)
                .withClaim("role", "ROLE_" + roles)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + securityConstant.getJwtExpiration()))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        return JWT.require(algorithm)
                .withIssuer(securityConstant.getJwtUserGenerator())
                .build()
                .verify(token);
    }

    public Long extractUserId(DecodedJWT jwt) {
        return jwt.getClaim("userId").asLong();
    }

    public String extractRole(DecodedJWT jwt) {
        return jwt.getClaim("role").asString();
    }

    public String extractUsername(DecodedJWT jwt) {
        return jwt.getSubject();
    }
}
