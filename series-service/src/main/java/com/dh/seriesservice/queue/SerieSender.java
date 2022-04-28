package com.dh.seriesservice.queue;

import com.dh.seriesservice.model.Serie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SerieSender {


    private final RabbitTemplate rabbitTemplate;
    // TODO: como sabes cual de todos los Queue inyectar aquí?
    private final Queue serieQueue;

    public void send(Serie serie){
        this.rabbitTemplate.convertAndSend( this.serieQueue.getName(), serie);
    }

}
