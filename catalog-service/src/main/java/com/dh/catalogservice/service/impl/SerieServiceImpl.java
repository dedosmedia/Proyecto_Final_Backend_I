package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.SerieRepository;
import com.dh.catalogservice.service.SerieService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class SerieServiceImpl implements SerieService {

    private SerieRepository serieRepository;
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public SerieServiceImpl(SerieRepository serieRepository, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.serieRepository = serieRepository;
        this.circuitBreakerRegistry = circuitBreakerRegistry;

        // Para mostrar el estado del CircuitBreaker
        circuitBreakerRegistry.circuitBreaker("series").getEventPublisher().onEvent( event -> {

            log.info("Event type:{}, CircuitBreaker state:{}, Failure Rate:{}",
                    event.getEventType(),
                    circuitBreakerRegistry.circuitBreaker("series").getState(),
                    circuitBreakerRegistry.circuitBreaker("series").getMetrics().getFailureRate()
                    );
        });
    }

    @CircuitBreaker( name = "series", fallbackMethod = "findByGenreFallback")
    @Retry( name = "series")
    @Override
    public List<Serie> findByGenre(String genre) {
        log.info("llamando por openFeign");
        return serieRepository.findByGenre(genre);
    }




    private List<Serie> findByGenreFallback(String genre, CallNotPermittedException ex){
        log.error("Circuito Abierto");
        return new ArrayList<>();
    }
}
