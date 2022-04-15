package com.dh.seriesservice.controller;

import com.dh.seriesservice.model.Serie;
import com.dh.seriesservice.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
@AllArgsConstructor
public class SerieController {

    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<Serie>> findAll(){
        return  ResponseEntity.ok().body(serieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> findById(@PathVariable String id){
        return  ResponseEntity.ok().body(serieService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Serie> findByName(@PathVariable String name){
        return  ResponseEntity.ok().body(serieService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Serie> findByName(@RequestBody Serie serie){
        System.out.println(serie);
        return  ResponseEntity.ok().body(serieService.createSerie(serie));
    }

}
