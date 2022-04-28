package com.dh.catalogservice.queue;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
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
public class SerieListener {

    private CatalogService catalogService;

    @RabbitListener( queues = { "${queue.serie.name}"})
    public void receive(@Payload Serie serie){
        log.info("He recibido una serie: {}", serie);


        // Existe el catalogo del a serie?

        List<Catalog> catalogList = catalogService.findByGenre(serie.getGenre());

        Catalog catalog;

        if ( catalogList.size() > 0)
        {
            // Si ya existe el genero traer las series que ya existen
            catalog = catalogList.get(0);
            List<Serie> serieList = catalog.getSeries();

            if( serieList != null){
                // Si la lista de movies existe, agregamos una serie
                serieList.add(serie);
            }
            else {
                // Si la lista de series no existe, la creamos con esta serie
                catalog.setSeries(Collections.singletonList(serie));
            }
        }
        else {
            // no existe el genero... debemos inicializarlo con la primer lista
            catalog = Catalog.builder().genre(serie.getGenre()).series( Collections.singletonList(serie)).build();
        }

        log.info("El catalogo se ha actualizado con: {}",catalogService.save( catalog));
    }
}
