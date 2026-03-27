package co.clean_architecture.usecase.user;

import co.clean_architecture.model.user.Mail;
import co.clean_architecture.model.user.Password;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.exception.EmailAlreadyExistsException;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.gateways.PasswordEncoderGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.usecase.user.command.UpdateUserCommand;
import co.clean_architecture.usecase.user.policy.PasswordPolicy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderGateway passwordEncoderGateway;

    public Mono<User> execute(Long id, UpdateUserCommand command) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new UserNotExistsException("User with id " + id + " does not exist")))
            .flatMap(existingUser ->
                Mono.when(
                    validateUsername(id, command),
                    validateMail(id, command)
                ).thenReturn(existingUser)
            )
            .map(user -> {
                if(command.password() != null && !command.password().isEmpty()) {
                    PasswordPolicy.validate(command.password());
                }
                return user.update(
                        command.username(),
                        buildPassword(command.password()),
                        buildMail(command.mail())
                );
            })
            .flatMap(userRepository::save);
    }

    private Mono<Void> validateUsername(Long id, UpdateUserCommand command) {
        if (command.username() == null) {
            return Mono.empty();
        }

        return userRepository.usernameHasOccupied(id, command.username())
            .flatMap(occupied -> {
                if (occupied) {
                    return Mono.error(new IllegalArgumentException("Username already in use"));
                }
                return Mono.empty();
            });
    }

    private Mono<Void> validateMail(Long id, UpdateUserCommand command) {
        if (command.mail() == null) {
            return Mono.empty();
        }

        return userRepository.mailHasOccupied(id, command.mail())
            .flatMap(occupied -> {
                if (occupied) {
                    return Mono.error(new EmailAlreadyExistsException("Email already in use"));
                }
                return Mono.empty();
        });
    }

    private Password buildPassword(String password) {
        if (password == null || password.isEmpty()) {
            return null;
        }
        return new Password(passwordEncoderGateway.encode(password));
    }

    private Mail buildMail(String mail) {
        if(mail == null || mail.isEmpty()) {
            return null;
        }
        return new Mail(mail);
    }

}

