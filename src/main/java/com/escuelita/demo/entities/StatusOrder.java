package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "status_orders")
@Getter
@Setter
public class StatusOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String status;
    @NotNull
    @NotBlank
    private String dateOrderReceived;

    private String dateOrderEnded;

    @OneToMany(mappedBy = "statusOrder")
    private List<Order> order;
}
