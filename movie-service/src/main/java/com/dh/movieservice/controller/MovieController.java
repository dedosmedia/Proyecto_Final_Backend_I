package com.dh.movieservice.controller;


import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    private MovieSender movieSender;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll(HttpServletRequest request, @AuthenticationPrincipal Jwt principal){

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

        return  ResponseEntity.ok().body(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(movieService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Movie> findByName(@PathVariable String name){
        return  ResponseEntity.ok().body(movieService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){

        Movie movieSaved = movieService.createMovie(movie);
        movieSender.send(movieSaved);
        log.info("Movie Saved: "+movieSaved);
        return  ResponseEntity.ok().body(movieSaved);
    }

}
