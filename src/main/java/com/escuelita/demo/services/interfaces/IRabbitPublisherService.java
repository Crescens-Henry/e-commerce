package com.escuelita.demo.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
public interface IRabbitPublisherService {
    void sendToRabbit(String message);
    void sendOrderProductToRabbit(String message);
    void sendStockProductToRabbit(String message);

    void send(Object message);
}
