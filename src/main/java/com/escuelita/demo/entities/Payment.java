package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "payments")
@Getter @Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

}
