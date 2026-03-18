package co.clean_architecture.usecase.user;

import co.clean_architecture.model.role.exception.RoleNotFoundException;
import co.clean_architecture.model.role.gateways.RoleRepository;
import co.clean_architecture.model.user.Mail;
import co.clean_architecture.model.user.Password;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.exception.EmailAlreadyExistsException;
import co.clean_architecture.model.user.exception.UsernameAlreadyExistsException;
import co.clean_architecture.model.user.gateways.PasswordEncoderGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.usecase.user.command.CreateUserCommand;
import co.clean_architecture.usecase.user.policy.PasswordPolicy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderGateway passwordEncoderGateway;

    public Mono<User> execute(CreateUserCommand command) {

        return userRepository.existsByUsername(command.username())
                .flatMap(existsUsername -> {
                    if (existsUsername) {
                        return Mono.error(new UsernameAlreadyExistsException("Username already exists"));
                    }

                    return userRepository.existsByEmail(command.email());
                })
                .flatMap(existsEmail -> {
                    if (existsEmail) {
                        return Mono.error(new EmailAlreadyExistsException("Email already exists"));
                    }

                    return roleRepository.findByName(command.roleName());
                })
                .switchIfEmpty(Mono.error(new RoleNotFoundException("Role not found")))
                .flatMap(role -> {

                    // Validaciones de dominio
                    Mail mail = new Mail(command.email());
                    PasswordPolicy.validate(command.password());

                    // Encode password (puede ser costoso)
                    return Mono.fromCallable(() ->
                                    passwordEncoderGateway.encode(command.password()))
                            .map(encodedPassword -> {

                                Password password = new Password(encodedPassword);

                                return User.create(
                                        command.username(),
                                        password,
                                        mail,
                                        role.getId()
                                );
                            });
                })
                .flatMap(userRepository::save);
    }
}
