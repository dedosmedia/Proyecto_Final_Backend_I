package com.dh.catalogservice.config;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
* Esta clase de configuraciónse agregó para permitir hacer Token Relay por Feign, a los demás microservicios.
* y así pasar el Authorization Token por los headers.
* */

public class FeignConfig {
    private static final Pattern BEARER_TOKEN_HEADER_PATTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
            Pattern.CASE_INSENSITIVE);
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            final String authorization = HttpHeaders.AUTHORIZATION;
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(requestAttributes)) {
                String authorizationHeader = requestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
                Matcher matcher = BEARER_TOKEN_HEADER_PATTERN.matcher(authorizationHeader);
                if (matcher.matches()) {
                    //  Clear token Head   Avoid contagion
                    requestTemplate.header(authorization);
                    requestTemplate.header(authorization, authorizationHeader);
                }
            }
        };
    }
}

