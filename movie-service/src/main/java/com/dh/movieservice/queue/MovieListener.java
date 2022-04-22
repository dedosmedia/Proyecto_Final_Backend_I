package com.dh.movieservice.queue;


import com.dh.movieservice.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MovieListener {

    @RabbitListener( queues = { "${queue.movie.name}"})
    public void receive(@Payload Movie movie){
        log.info("He recibido: {}",movie);
    }
}
