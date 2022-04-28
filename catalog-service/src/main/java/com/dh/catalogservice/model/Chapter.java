package com.dh.catalogservice.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Chapter {

    private String id;
    private String name;
    private Number number;
    private String urlStream;

}
