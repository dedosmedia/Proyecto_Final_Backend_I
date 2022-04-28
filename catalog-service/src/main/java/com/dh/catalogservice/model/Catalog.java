package com.dh.catalogservice.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Document ( collection = "catalogs")
public class Catalog {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String genre;

    private List<Movie> movies;
    private List<Serie> series;

    public Catalog(String genre, List<Movie> movies, List<Serie> series) {
        this.genre = genre;
        this.movies = movies;
        this.series = series;
    }
}
