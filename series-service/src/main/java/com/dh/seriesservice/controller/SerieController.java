package com.dh.seriesservice.controller;

import com.dh.seriesservice.model.Serie;
import com.dh.seriesservice.service.SerieService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



@Slf4j
@RestController
@RequestMapping("/series")
@AllArgsConstructor
public class SerieController {

    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<Serie>> findAll(HttpServletRequest request){

        log.info("Authorization bearer {}", request.getHeader(HttpHeaders.AUTHORIZATION));
        return  ResponseEntity.ok().body(serieService.findAll());
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> findById(@PathVariable String genre){
        return  ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<Serie> findByName(@RequestBody Serie serie){
        System.out.println(serie);
        return  ResponseEntity.ok().body(serieService.save(serie));
    }

    @GetMapping("/resource")
    public String resource(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Resource accessed by: %s (with subjectId: %s)" ,
                jwt.getClaims().get("user_name"),
                jwt.getSubject());
    }


}
