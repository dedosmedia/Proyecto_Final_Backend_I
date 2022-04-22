package com.dh.movieservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {

    @Value("MovieQueue")
    private String movieQueue;

    // TODO: No debería llamarse movieQueue el método?
    // Como lo sabe inyectar luego?
    @Bean
    public Queue queue(){
        return new Queue(this.movieQueue, true);
    }
}
