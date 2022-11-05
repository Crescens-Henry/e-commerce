package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetShippingResponse {

    private Long id;
    private String dataExit;
    private String dataReceived;
}
