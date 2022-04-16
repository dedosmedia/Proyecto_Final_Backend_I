package com.dh.gatewayservice.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.dh.gatewayservice.filter.OIDCTokenRelay;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.cloud.gateway.enabled", matchIfMissing = true)
@ConditionalOnClass({ OAuth2AuthorizedClient.class, SecurityWebFilterChain.class, SecurityProperties.class })
@ConditionalOnEnabledFilter(OIDCTokenRelay.class)
public class OIDCTokenRelayConfiguration {
    @Bean
    public OIDCTokenRelay myOIDCTokenRelay() {
        return new OIDCTokenRelay();
    }
}
