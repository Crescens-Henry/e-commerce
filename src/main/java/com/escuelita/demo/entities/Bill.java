package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

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

    @OneToOne(mappedBy = "bill", fetch = FetchType.LAZY)
    private Order order;
}
