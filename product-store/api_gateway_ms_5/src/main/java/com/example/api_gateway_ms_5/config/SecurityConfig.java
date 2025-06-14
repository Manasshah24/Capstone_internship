package com.example.api_gateway_ms_5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/products/**").hasRole("ADMIN")
                        .pathMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
                        .pathMatchers("/inventory/**").hasRole("ADMIN")
                        .anyExchange().permitAll() // Allow unauthenticated access to other endpoints
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri("http://localhost:8080/realms/capstone_store/protocol/openid-connect/certs")
                                .jwtAuthenticationConverter(new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter()))
                        ))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return converter;
    }

    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return jwt -> {
            Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access"); // Use getClaimAsMap
            if (resourceAccess == null) {
                return Collections.emptyList();
            }

            Map<String, Object> client = (Map<String, Object>) resourceAccess.get("product-store"); // Your client ID
            if (client == null) {
                return Collections.emptyList();
            }

            List<String> roles = (List<String>) client.get("roles");
            if (roles == null) {
                return Collections.emptyList();
            }

            return roles.stream()
                    .map(role -> role.replace("ROLE_", "")) // Remove "ROLE_" prefix if present
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Add "ROLE_" prefix
                    .collect(Collectors.toList());
        };
    }

    private static class ReactiveJwtAuthenticationConverterAdapter
            implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

        private final JwtAuthenticationConverter delegate;

        public ReactiveJwtAuthenticationConverterAdapter(JwtAuthenticationConverter delegate) {
            this.delegate = delegate;
        }

        @Override
        public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
            return Mono.just(delegate.convert(jwt));
        }
    }
}