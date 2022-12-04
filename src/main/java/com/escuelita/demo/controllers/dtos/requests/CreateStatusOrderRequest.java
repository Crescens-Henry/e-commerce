package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateStatusOrderRequest {

    @NotNull
    @NotBlank
    private String status;
    @NotNull
    @NotBlank
    private String dateOrderReceived;
    private String dateOrderEnded;
}
