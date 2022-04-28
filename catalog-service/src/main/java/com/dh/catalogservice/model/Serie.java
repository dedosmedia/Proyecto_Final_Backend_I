package com.dh.catalogservice.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Serie {


    private String id;

    private String name;
    private String genre;
    private List<Season> seasons;


}
