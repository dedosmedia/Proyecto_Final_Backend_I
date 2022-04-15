package com.dh.movieservice.service;

import com.dh.movieservice.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();
    Movie findByName(String name);
    Movie findById(Long id);
    Movie createMovie(Movie movie);

}
