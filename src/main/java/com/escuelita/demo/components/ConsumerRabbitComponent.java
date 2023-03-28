package com.escuelita.demo.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerRabbitComponent {
    private void makeSlow() throws InterruptedException {
        Thread.sleep(5000);
    }
}
