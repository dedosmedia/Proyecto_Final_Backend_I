package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MovieRepository;
import com.dh.catalogservice.repository.SerieRepository;
import com.dh.catalogservice.service.MovieService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public MovieServiceImpl(MovieRepository movieRepository, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.movieRepository = movieRepository;
        this.circuitBreakerRegistry = circuitBreakerRegistry;

        // Para mostrar el estado del CircuitBreaker
        circuitBreakerRegistry.circuitBreaker("movies").getEventPublisher().onEvent( event -> {

            log.info("Event type:{}, CircuitBreaker state:{}, Failure Rate:{}",
                    event.getEventType(),
                    circuitBreakerRegistry.circuitBreaker("movies").getState(),
                    circuitBreakerRegistry.circuitBreaker("movies").getMetrics().getFailureRate()
                    );
        });
    }

    @CircuitBreaker( name = "movies", fallbackMethod = "findByGenreFallback")
    @Retry( name = "movies")
    @Override
    public List<Serie> findByGenre(String genre) {
        log.info("llamando por openFeign");
        return movieRepository.findByGenre(genre);
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.saveMovie(movie);
    }

    private List<Serie> findByGenreFallback(String genre, CallNotPermittedException ex){
        log.error("Circuito Abierto");
        return new ArrayList<>();
    }
}
