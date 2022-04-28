package com.dh.catalogservice.repository;

import com.dh.catalogservice.config.FeignConfig;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient( name = "movie-service", configuration = {FeignConfig.class })
public interface MovieRepository {

    @GetMapping("/movies/{genre}")
    List<Serie> findByGenre(@PathVariable String genre);


    @PostMapping("/movies")
    Movie saveMovie(@RequestBody Movie movie);
}
