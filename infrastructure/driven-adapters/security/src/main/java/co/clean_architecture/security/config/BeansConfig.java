package co.clean_architecture.security.config;

import co.clean_architecture.model.security.gateways.TokenGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.security.filter.JwtAuthenticationFilter;
import co.clean_architecture.security.filter.UserStatusFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(
            ReactiveUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        var authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authManager.setPasswordEncoder(passwordEncoder);
        return authManager;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(TokenGateway tokenGateway) {
        return new JwtAuthenticationFilter(tokenGateway);
    }

    @Bean
    public UserStatusFilter userStatusFilter(UserRepository userRepository) {
        return new UserStatusFilter(userRepository);
    }
}