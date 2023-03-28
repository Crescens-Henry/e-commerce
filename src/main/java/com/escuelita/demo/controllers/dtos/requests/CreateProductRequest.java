package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CreateProductRequest {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    private Double price;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @Min(value = 1)
    @Max(value = 600, message = "No hay mucho espacio para todos esos pasteles")
    private Integer quantity;

    private String cakePicture;
}
