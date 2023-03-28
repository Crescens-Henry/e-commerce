package com.escuelita.demo.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class PublisherRabbitComponent {
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    // TODO: pendiente el tipo de coloa inicial
    @Value("${rabbitmq.queue.init}")
    private String queueInit;
    @Value("${rabbitmq.queue.order-product}")
    private String queueOrderProduct;
    @Value("${rabbitmq.queue.stock-product}")
    private String queueStockProduct;

    public void sendToInit(String message){
        rabbitTemplate.convertAndSend(queueInit, message );
    }
    public void sendToOrderProduct(String message){
        rabbitTemplate.convertAndSend(queueOrderProduct, message );
    }
    public void sendToStockProduct(String message){
        rabbitTemplate.convertAndSend(queueStockProduct, message );
    }
}
