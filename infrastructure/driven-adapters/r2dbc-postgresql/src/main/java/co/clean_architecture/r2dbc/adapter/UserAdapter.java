package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.r2dbc.mapper.UserMapper;
import co.clean_architecture.r2dbc.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserRepository {

    private final UserR2dbcRepository userR2dbcRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> save(User user) {
        return userR2dbcRepository
            .save(userMapper.toEntity(user))
            .map(userMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return userR2dbcRepository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return userR2dbcRepository.existsByUsername(username);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userR2dbcRepository
            .findByUsername(username)
            .map(userMapper::toDomain);
    }

    @Override
    public Mono<Boolean> userStatusIsActive(Long id) {
        return userR2dbcRepository.userStatusIsActive(id);
    }
}
