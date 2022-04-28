package com.dh.catalogservice.queue;


import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class MovieListener {

    CatalogService catalogService;

    @RabbitListener( queues = { "${queue.movie.name}"})
    public void receive(@Payload Movie movie){


        log.info("He recibido una movie: {}",movie);

        // Existe el catalogo para el genero de esta movie?
        List<Catalog> catalogList = catalogService.findByGenre(movie.getGenre());

        Catalog catalog;

        if ( catalogList.size() > 0)
        {
            // Si ya existe el genero traer las series que ya existen
            catalog = catalogList.get(0);
            List<Movie> moviesList = catalog.getMovies();

            if (moviesList != null){
                // Si la lista de movies existe, agregamos una movie
                moviesList.add(movie);
            }
            else {
                // Si la lista de movies no existe, la creamos con esta movie
                catalog.setMovies(Collections.singletonList(movie));
            }

        }
        else {
            // no existe el genero... debemos inicializarlo con la primer lista
            catalog = Catalog.builder().genre(movie.getGenre()).movies( Collections.singletonList(movie)).build();
        }

        log.info("El catalogo se ha actualizado con: {}",catalogService.save( catalog));


    }
}
