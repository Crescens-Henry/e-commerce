package com.escuelita.demo.controllers.dtos.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    @NotNull
    @NotBlank
    private String date;
    @NotNull
    private Long clientId;
    @NotNull
    private Long shippingId;
    @NotNull
    private Long billId;
    @NotNull
    private Long statusOrderId;

}
