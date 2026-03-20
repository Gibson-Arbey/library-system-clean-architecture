package co.clean_architecture.security.adapter;

import co.clean_architecture.model.user.gateways.AuthenticationGateway;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationGateway {

    private final ReactiveAuthenticationManager authenticationManager;

    @Override
    public Mono<Void> authenticate(String username, String password) {

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        ).then(); // solo valida credenciales
    }
}