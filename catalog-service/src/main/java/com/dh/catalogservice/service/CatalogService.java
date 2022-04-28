package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;

import java.util.List;

public interface CatalogService {

    List<Catalog> findAll();

    List<Movie> findByGenreAndMovies(String genre);

    List<Catalog> findByGenre(String genre);

    Catalog save(Catalog catalog);

    List<Catalog> findCatalogsByGenre(String genre);

}
