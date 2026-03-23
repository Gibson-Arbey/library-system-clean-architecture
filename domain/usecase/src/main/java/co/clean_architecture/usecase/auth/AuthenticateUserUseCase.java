package co.clean_architecture.usecase.auth;

import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.role.gateways.RoleRepository;
import co.clean_architecture.model.security.AuthResult;
import co.clean_architecture.model.security.gateways.TokenGateway;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.AuthenticationGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticateUserUseCase {

    private final AuthenticationGateway  authenticationGateway;
    private final TokenGateway tokenGateway;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Mono<AuthResult> login(String username, String password) {
        return authenticationGateway.authenticate(username, password)
                // 1. credenciales válidas
                .then(userRepository.findByUsername(username))
                // 2. usuario
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                // 3. generar respuesta
                .flatMap(user ->
                        roleRepository.findById(user.getRoleId())
                                .switchIfEmpty(Mono.error(new RuntimeException("Role not found")))
                                .map(role -> buildAuthResult(user, role))
                );
    }

    private AuthResult buildAuthResult(User user, Role role) {

        String roleName = role.getName();

        String token = tokenGateway.generateToken(
                user.getId(),
                user.getUsername(),
                roleName
        );

        return AuthResult.of(
                user.getId(),
                user.getUsername(),
                user.getMail().value(),
                roleName,
                token
        );
    }
}
