package co.clean_architecture.model.user.gateways;

import co.clean_architecture.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByUsername(String username);

    Mono<User> findByUsername(String username);

    Mono<Boolean> userStatusIsActive(Long id);

    Mono<User> findById(Long id);

    Mono<Boolean> existsById(Long id);

    Mono<Void> updateUserStatus(Long id, String status);
}
