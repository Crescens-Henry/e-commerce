package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderResponse implements Serializable {

    private Long id;
    private String date;
    private Long clientId;
    private Long shippingId;
    private Long billId;
    private Long statusOrderId;
    private String socketId;

}
