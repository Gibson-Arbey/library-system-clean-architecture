package co.clean_architecture.security.filter;

import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.security.exception.UserStatusException;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserStatusFilter implements WebFilter {

    private final UserRepository userRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        return ReactiveSecurityContextHolder.getContext()
                .flatMap(securityContext -> {
                    var authentication = securityContext.getAuthentication();

                    if (authentication != null && authentication.isAuthenticated()) {

                        Long userId = (Long) authentication.getDetails();

                        return userRepository.userStatusIsActive(userId)
                                .flatMap(isActive -> {
                                    if (!isActive) {
                                        return Mono.error(
                                                new UserStatusException("User account is not active.")
                                        );
                                    }
                                    return chain.filter(exchange);
                                });
                    }

                    return chain.filter(exchange);
                })
                .switchIfEmpty(chain.filter(exchange));
    }
}