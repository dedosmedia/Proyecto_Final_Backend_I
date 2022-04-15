package com.dh.seriesservice.service.impl;


import com.dh.seriesservice.model.Serie;
import com.dh.seriesservice.repository.SerieRepository;
import com.dh.seriesservice.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SerieServiceImpl implements SerieService {

    private SerieRepository serieRepository;

    @Override
    public List<Serie> findAll() {
        return serieRepository.findAll();
    }

    @Override
    public List<Serie> findAllByGenre(String genre) {
        return serieRepository.findByGenre(genre);
    }

    @Override
    public Serie findByName(String name) {
        return serieRepository.findByName(name);
    }

    @Override
    public Serie findById(String id) {
        return serieRepository.findById(id).orElse(null);
    }

    @Override
    public Serie createSerie(Serie serie) {
        return serieRepository.save(serie);
    }
}
