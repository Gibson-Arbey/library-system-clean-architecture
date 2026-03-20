package co.clean_architecture.security.adapter;

import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.role.gateways.RoleRepository;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.UserStatus;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.exception.UserStatusNotValidException;
import co.clean_architecture.model.user.gateways.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(
                        new UserNotExistsException("User not found")
                ))
                .flatMap(user -> {
                    if (!user.getStatus().equals(UserStatus.ACTIVE.name())) {
                        return Mono.error(
                                new UserStatusNotValidException("User is not active")
                        );
                    }

                    return roleRepository.findById(user.getRoleId())
                            .switchIfEmpty(Mono.error(
                                    new RuntimeException("Role not found")
                            ))
                            .map(role ->
                                    org.springframework.security.core.userdetails.User
                                            .withUsername(user.getUsername())
                                            .password(user.getPassword().value())
                                            .authorities(
                                                    Collections.singletonList(
                                                            new SimpleGrantedAuthority("ROLE_" + role.getName())
                                                    )
                                            )
                                            .accountLocked(false)
                                            .disabled(false)
                                            .build()
                            );
                });
    }
}