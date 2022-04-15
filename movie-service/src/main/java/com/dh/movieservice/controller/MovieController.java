package com.dh.movieservice.controller;


import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;


    @GetMapping
    public ResponseEntity<List<Movie>> findAll(){
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
    public ResponseEntity<Movie> findByName(@RequestBody Movie movie){
        System.out.println(movie);
        return  ResponseEntity.ok().body(movieService.createMovie(movie));
    }

}
