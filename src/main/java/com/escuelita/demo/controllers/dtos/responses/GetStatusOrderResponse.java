package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetStatusOrderResponse {

    private Long id;
    private String status;
    private String dateOrderReceived;
    private String dateOrderEnded;

}
