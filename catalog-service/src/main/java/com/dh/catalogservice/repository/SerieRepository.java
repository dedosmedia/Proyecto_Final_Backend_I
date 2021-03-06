package com.dh.catalogservice.repository;

import com.dh.catalogservice.config.FeignConfig;
import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient( name = "serie-service", configuration = {FeignConfig.class })
public interface SerieRepository {


    /*
    El @PathVariable fue obligatorio, en caso contrario el Feign me estaba pasando la petición GET como POST
    al otro microservicio y rompía..
     */
    @GetMapping("/series/{genre}")
    List<Serie> findByGenre(@PathVariable String genre);


    @PostMapping("/series")
    Serie saveSerie(@RequestBody Serie serie);
}
