package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.ProductOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String date;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Shipping shipping;

    @ManyToOne
    private Bill bill;

    @ManyToOne
    private StatusOrder statusOrder;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> productOrders;

}
