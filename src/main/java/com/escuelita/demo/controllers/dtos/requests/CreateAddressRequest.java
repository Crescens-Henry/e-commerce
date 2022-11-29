package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateAddressRequest {

    @NotNull
    @NotBlank
    private String state;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    private String houseNumber;
    @NotNull
    private Integer zipCode;
    @NotNull
    private Long clientId;
}
