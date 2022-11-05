package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdateBillRequest {
    @NotNull
    private Double amount;
    @NotNull @NotBlank
    private String date;
}
