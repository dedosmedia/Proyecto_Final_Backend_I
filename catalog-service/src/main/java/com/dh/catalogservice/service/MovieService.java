package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;

import java.util.List;

public interface MovieService {

    List<Serie>  findByGenre(String genre);
    Movie saveMovie(Movie movie);
}
