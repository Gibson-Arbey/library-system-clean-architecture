package co.clean_architecture.security.config;

import co.clean_architecture.security.filter.JwtAuthenticationFilter;
import co.clean_architecture.security.filter.UserStatusFilter;
import co.clean_architecture.security.handler.JwtAccessDeniedHandler;
import co.clean_architecture.security.handler.JwtAuthenticationHandler;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserStatusFilter userStatusFilter;
    private final JwtAuthenticationHandler jwtAuthenticationHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationHandler)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .authorizeExchange(auth -> auth
                        .pathMatchers("/api/v1/auth/login", "/api/v1/user").permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtAuthenticationFilter, AUTHENTICATION)
                .addFilterAfter(userStatusFilter, AUTHENTICATION)

                .build();
    }
}