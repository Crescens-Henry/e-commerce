package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String description;

    private Integer quantity;
    private String cakePicture;
}
