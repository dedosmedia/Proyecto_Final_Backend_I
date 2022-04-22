package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.repository.CatalogRepository;
import com.dh.catalogservice.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public List<Movie> findByGenreAndMovies(String genre) {
        return catalogRepository.findByGenreAndMovies(genre);
    }

    @Override
    public Catalog save(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public List<Catalog> findCatalogsByGenre(String genre) {
        return catalogRepository.findCatalogsByGenre(genre);
    }
}
