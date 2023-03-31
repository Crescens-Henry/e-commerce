package com.escuelita.demo.services;

import com.escuelita.demo.components.PublisherRabbitComponent;
import com.escuelita.demo.services.interfaces.IRabbitPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitPublisherServiceImpl implements IRabbitPublisherService {
    @Autowired
    private final PublisherRabbitComponent publisher;
    public RabbitPublisherServiceImpl(PublisherRabbitComponent publisher) {
        this.publisher = publisher;
    }

    @Override
    public void sendToRabbit(String message) {
        log.info("Message '{}', will be send...",message);
        publisher.sendToInit(message);
    }

    @Override
    public void sendOrderProductToRabbit(String message) {
        log.info("Message '{}', will be send...",message);
        publisher.sendToOrderProduct(message);
    }

    @Override
    public void sendStockProductToRabbit(String message) {
        log.info("Message '{}', will be send...",message);
        publisher.sendToStockProduct(message);
    }

    @Override
    public void send(Object message) {
        log.info("Message '{}', will be send...",message);
        publisher.send(message);
    }
}
