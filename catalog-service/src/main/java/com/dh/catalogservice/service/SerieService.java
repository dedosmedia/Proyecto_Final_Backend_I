package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Serie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SerieService {

    List<Serie>  findByGenre(String genre);
}
