package com.dh.catalogservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {

    @Value("${queue.movie.name}")
    private String movieQueue;

    @Value("${queue.serie.name}")
    private String serieQueue;


    @Bean
    public Queue serieQueue(){
        return new Queue(this.serieQueue, true);
    }

    @Bean
    public Queue movieQueue(){
        return new Queue(this.movieQueue, true);
    }

//
//    @Bean
//    DirectExchange exchangeSerie() {
//        return new DirectExchange(this.serieQueue);
//    }
//
//    @Bean
//    DirectExchange exchangeMovie() {
//        return new DirectExchange(this.movieQueue);
//    }
//
//
//
//    @Bean
//    public Binding bindingSerieQueue(DirectExchange exchangeSerie,
//                                     Queue serieQueue) {
//        return BindingBuilder.bind(serieQueue)
//                .to(exchangeSerie)
//                .with(this.serieQueue);
//    }
//
//    @Bean
//    public Binding bindingMovieQueue(DirectExchange exchangeMovie,
//                                     Queue movieQueue) {
//        return BindingBuilder.bind(movieQueue)
//                .to(exchangeMovie)
//                .with(this.movieQueue);
//    }

}
