package com.dh.catalogservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Document ( collection = "catalogs")
public class Catalog {
    @Id
    private String id;

    private String genre;

    private List<Movie> movies;
    private List<Serie> series;

    public Catalog(String genre, List<Movie> movies, List<Serie> series) {
        this.genre = genre;
        this.movies = movies;
        this.series = series;
    }
}
