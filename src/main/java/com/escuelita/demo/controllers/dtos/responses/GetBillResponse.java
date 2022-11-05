package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetBillResponse {
    private Long id;
    private Double amount;
    private Double iva;
    private String date;
}
