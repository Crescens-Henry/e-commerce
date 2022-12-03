package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bills")
@Getter
@Setter
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double amount;
    @NotNull
    private Double iva;
    @NotNull
    @NotBlank
    private String date;

    @OneToMany(mappedBy = "bill")
    private List<Order> order;
}
