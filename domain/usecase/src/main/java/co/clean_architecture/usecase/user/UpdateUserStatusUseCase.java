package co.clean_architecture.usecase.user;

import co.clean_architecture.model.user.UserStatus;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.usecase.user.command.UpdateUserStatusCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor
public class UpdateUserStatusUseCase {

    private final UserRepository userRepository;

    public Mono<Void> execute(UpdateUserStatusCommand command) {

        return userRepository.findById(command.id())
                .switchIfEmpty(Mono.error(new UserNotExistsException("User not exists")))
                .flatMap(user -> {
                    boolean isValidStatus = Arrays.stream(UserStatus.values())
                            .anyMatch(status -> status.name().equals(command.status()));
                    if (!isValidStatus) {
                        return Mono.error(new IllegalArgumentException("Invalid user status"));
                    }
                    return userRepository.updateUserStatus(command.id(), command.status());
                });
    }
}
