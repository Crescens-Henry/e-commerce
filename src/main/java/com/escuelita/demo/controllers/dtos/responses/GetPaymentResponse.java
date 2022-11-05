package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetPaymentResponse {

    private Long id;
    private String cardNumber;
    private String cardHolder;
    private Long clientId;
}
