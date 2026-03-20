package co.clean_architecture.model.user.gateways;

import reactor.core.publisher.Mono;

public interface AuthenticationGateway {

    Mono<Void> authenticate(String username, String password);
}
