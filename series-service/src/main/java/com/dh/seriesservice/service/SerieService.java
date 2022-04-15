package com.dh.seriesservice.service;

import com.dh.seriesservice.model.Serie;

import java.util.List;

public interface SerieService {

    List<Serie> findAll();
    Serie findByName(String name);
    Serie findById(String id);
    List<Serie> findAllByGenre(String genre);
    Serie createSerie(Serie movie);

}
