package com.dh.catalogservice.controller;


import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.service.MovieService;
import com.dh.catalogservice.service.SerieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private CatalogService catalogService;
    private SerieService serieService;
    private MovieService movieService;


    @GetMapping
    public ResponseEntity<List<Catalog>> findAll(){
        return ResponseEntity.ok().body( catalogService.findAll());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Catalog>> findAllByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(catalogService.findCatalogsByGenre(genre));
    }

    @GetMapping("/series/{genre}/{error}")
    public ResponseEntity<List<Serie>> findSeriesByGenre(@PathVariable String genre, @PathVariable Boolean error){
        if( error == true)
            throw new RuntimeException();
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @GetMapping("/movies/{genre}")
    public ResponseEntity<List<Movie>> findAllMovieByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(catalogService.findByGenreAndMovies(genre));
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        // Si quisieramos persistir directamente en catalogo
        // Catalog catalog = new Catalog( movie.getGenre(), Collections.singletonList(movie), null);
        //return ResponseEntity.ok().body(catalogService.save(catalog));

        // Invocar por Feign, al MS de Series para persistir los datos allá
        return ResponseEntity.ok().body(movieService.saveMovie(movie));
    }

    @PostMapping("/series")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie){
        // Si quisieramos persistir directamente en catalogo
        //Catalog catalog = new Catalog( serie.getGenre(), null, Collections.singletonList(serie));
        //return ResponseEntity.ok().body(catalogService.save(catalog));

        // Invocar por Feign, al MS de Series para persistir los datos allá
        return ResponseEntity.ok().body(serieService.saveSerie(serie));
    }


    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Catalog>> findByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(catalogService.findByGenre(genre));
    }

}
