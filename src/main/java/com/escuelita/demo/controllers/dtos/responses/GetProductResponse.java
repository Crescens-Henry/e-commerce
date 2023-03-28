package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductResponse {

    private Long id;
    private String name;
    private String type;
    private Double price;
    private String description;
    private Integer quantity;
    private String cakePicture;
}
