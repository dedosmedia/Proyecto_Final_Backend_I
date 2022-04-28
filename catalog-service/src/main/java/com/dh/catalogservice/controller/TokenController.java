package com.dh.catalogservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {


    @GetMapping
    public String user(HttpServletRequest request) {

        // Desde el bearer token.
        log.info("\n===================\n");
        log.info("Authorization header: {}", request.getHeader(HttpHeaders.AUTHORIZATION));
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
