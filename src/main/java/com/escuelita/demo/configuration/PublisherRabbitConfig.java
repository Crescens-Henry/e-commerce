package com.escuelita.demo.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherRabbitConfig {
    @Value("${rabbitmq.queue.init}")
    private String queue;

    @Bean
    public Queue queue(){
        return  new Queue(queue, true);
    }
}
