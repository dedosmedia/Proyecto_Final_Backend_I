package com.dh.seriesservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



@NoArgsConstructor
@Getter @Setter
@Document( collection = "series_db")
public class Serie {

    @Id
    private String id;

    private String name;
    private String genre;
    private List<Season> seasons;

    public Serie(String name, String genre, List<Season> seasons) {
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }
}
