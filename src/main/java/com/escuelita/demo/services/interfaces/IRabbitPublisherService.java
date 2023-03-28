package com.escuelita.demo.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
public interface IRabbitPublisherService {
    void sendToRabbit(String message);
    void sendOrderProductToRabbit(String message);// poner el queue en crear la orden
    void sendStockProductToRabbit(String message);// // poner el queue en crear el producto
}
