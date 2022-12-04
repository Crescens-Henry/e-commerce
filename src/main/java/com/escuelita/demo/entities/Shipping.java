package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "shippings")
@Getter
@Setter
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateExit;
    private String dateReceived;
    @OneToMany(mappedBy = "shipping")
    private List<Order> order;
}
