package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class UpdateClientRequest {
    @Email
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String password;
    @NotNull @Pattern(regexp="^\\(?(\\d{10})$", message="Mobile number is invalid")
    private String phone;
}
