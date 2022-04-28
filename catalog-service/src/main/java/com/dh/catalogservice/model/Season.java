package com.dh.catalogservice.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Season {

    private String id;
    private Number seasonNumber;
    private List<Chapter> chapters;

}
