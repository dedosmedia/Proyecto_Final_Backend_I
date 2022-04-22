package com.dh.gatewayservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @CircuitBreaker(name = "catalogService")
    @GetMapping("/catalog")
    public ResponseEntity<String> catalogFallback(){
        return ResponseEntity.ok().body("El servicio de catálogo está temporalmente fuera de servicio.");
    }

    @GetMapping("/serie")
    public ResponseEntity<String> serieFallback(){
        return ResponseEntity.ok().body("El servicio de series está temporalmente fuera de servicio.");
    }

    @GetMapping("/movie")
    public ResponseEntity<String> movieFallback(){
        return ResponseEntity.ok().body("El servicio de movies está temporalmente fuera de servicio.");
    }
}
