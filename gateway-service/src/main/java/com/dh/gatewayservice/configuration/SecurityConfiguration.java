package com.dh.gatewayservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository client) throws Exception {
        http.oauth2Login();
        http.csrf().disable();
        http.logout(logoutSpec -> logoutSpec.logoutSuccessHandler(
                new OidcClientInitiatedServerLogoutSuccessHandler(client)
        ));
        http.authorizeExchange().anyExchange().authenticated();
        http.cors().disable();
        return http.build();
    }
}