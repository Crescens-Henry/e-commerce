package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    private String lastName;
    @Pattern(regexp = "^\\(?(\\d{10})$", message = "Mobile number is invalid")
    private String phone;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Payment.class)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Payment> payment;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Address.class)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Address> address;

    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Order.class)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Order> order;

}
