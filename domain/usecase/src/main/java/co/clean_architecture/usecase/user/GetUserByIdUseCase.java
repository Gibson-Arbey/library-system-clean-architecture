package co.clean_architecture.usecase.user;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public Mono<User> execute(Long id) {
        return userRepository
            .findById(id)
            .switchIfEmpty(
                Mono.error(new UserNotExistsException("User with id " + id + " does not exist"))
            );
    }

}
