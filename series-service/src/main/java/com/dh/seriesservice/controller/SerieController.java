package com.dh.seriesservice.controller;

import com.dh.seriesservice.model.Serie;
import com.dh.seriesservice.queue.SerieSender;
import com.dh.seriesservice.service.SerieService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;


import javax.servlet.http.HttpServletRequest;
import java.util.List;



@Slf4j
@RestController
@RequestMapping("/series")
@AllArgsConstructor
public class SerieController {

    private SerieService serieService;
    private SerieSender serieSender;

    @GetMapping
    public ResponseEntity<List<Serie>> findAll(HttpServletRequest request, @AuthenticationPrincipal Jwt principal){

        // Desde el bearer token.
        log.info("\n===================\n");
        log.info("Authorization header: {}", request.getHeader(HttpHeaders.AUTHORIZATION));

        // Desde el security context.
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        log.info("\n===================\n");
        log.info("Authentication: {}", authentication);
        log.info("Principal: {}", authentication.getPrincipal());
        log.info("Credentials: {}", authentication.getCredentials());
        log.info("Name: {}", authentication.getName());
        log.info("Details: {}", authentication.getDetails());
        log.info("Authorities: {}", authentication.getAuthorities());

        // Desde el JWT directamente.
        log.info("\n===================\n");
        log.info("Claims: {}", principal.getClaims());
        log.info("Headers: {}", principal.getHeaders());
        log.info("Audiences: {}", principal.getAudience());
        log.info("Issuer: {}", principal.getIssuer());
        log.info("Expiry date: {}", principal.getExpiresAt());
        return  ResponseEntity.ok().body(serieService.findAll());
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> findByGenre(@PathVariable String genre){

        return  ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie){
        Serie serieSaved = serieService.save(serie);

        // Enviamos la serie creada al Rabbit, para que la consuma Catalog
        serieSender.send(serieSaved);
        return  ResponseEntity.ok().body(serieSaved);
    }

    @GetMapping("/resource")
    public String resource(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Resource accessed by: %s (with subjectId: %s)" ,
                jwt.getClaims().get("user_name"),
                jwt.getSubject());
    }


}
