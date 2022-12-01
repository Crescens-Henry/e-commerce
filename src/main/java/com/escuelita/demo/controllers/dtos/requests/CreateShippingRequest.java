package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateShippingRequest {
    private String dateExit;
    @NotNull
    @NotBlank
    private String dateReceived;
}
