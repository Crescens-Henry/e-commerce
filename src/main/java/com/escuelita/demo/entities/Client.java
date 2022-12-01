package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @Pattern(regexp = "^\\(?(\\d{10})$", message = "Mobile number is invalid")
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Payment> payment;

    @OneToMany(mappedBy = "client")
    private List<Address> address;

    @OneToMany(mappedBy = "client")
    private List<Order> order;

}
