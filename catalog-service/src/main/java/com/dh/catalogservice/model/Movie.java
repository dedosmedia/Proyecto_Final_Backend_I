package com.dh.catalogservice.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Movie {

    private String id;
    private String name;
    private String genre;
    private String urlStream;
}
