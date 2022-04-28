package com.dh.movieservice.queue;

import com.dh.movieservice.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieSender {


    private final RabbitTemplate rabbitTemplate;
    // TODO: como sabes cual de todos los Queue inyectar aquí?
    private final Queue movieQueue;

    public void send(Movie movie){
        this.rabbitTemplate.convertAndSend( this.movieQueue.getName(), movie);
    }

}
