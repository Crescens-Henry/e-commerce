package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UpdateProductRequest {

    @NotNull
    private Double price;
    @NotNull
    @Min(value = 1)
    @Max(value = 600, message = "No hay mucho espacio para todos esos pasteles")
    private Integer quantity;
    private String cakePicture;
}
