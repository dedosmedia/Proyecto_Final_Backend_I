package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {

    List<Movie> findByGenreAndMovies(String genre);


    List<Catalog> findCatalogsByGenre(String genre);



}
