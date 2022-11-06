package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private String date;
    private Long clientId;
    private Long shippingId;
    private Long billId;
    private Long statusOrderId;

}
