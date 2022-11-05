package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class CreatePaymentRequest {
    @NotNull @NotBlank @Pattern(regexp="^\\(?(\\d{16})$", message="Card Number is invalid")
    private String cardNumber;
    @NotNull @NotBlank
    private String dateExpiry;
    @NotNull @NotBlank
    private String cardHolder;
    @NotNull @NotBlank
    private String cardIssuer;
    @NotNull @NotBlank @Pattern(regexp="^\\(?(\\d{3})$", message="Cvv is invalid")
    private String cvv;
    @NotNull
    private Long clientId;
}
