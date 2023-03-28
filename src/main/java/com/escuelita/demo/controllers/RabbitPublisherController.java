package com.escuelita.demo.controllers;

import com.escuelita.demo.services.interfaces.IRabbitPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("rabbit")
public class RabbitPublisherController {
    @Autowired
    private final IRabbitPublisherService service;
    public RabbitPublisherController(IRabbitPublisherService service) {
        this.service = service;
    }
    @GetMapping("init")
    public Boolean testSendMessage(){
        String message = "message Test #"+ ThreadLocalRandom.current().nextInt();
        service.sendToRabbit(message);
        return true;
    }
}
