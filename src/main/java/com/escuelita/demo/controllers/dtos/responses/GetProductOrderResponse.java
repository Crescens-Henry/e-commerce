package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductOrderResponse {
    private Long id;
    private ProductResponse product;
    private OrderResponse order;
}
