package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.ProductOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    private Double price;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @Min(value = 1)
    @Max(value = 600, message = "No hay mucho espacio para todos esos pasteles")
    private Integer quantity;

    private String cakePicture;

    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders;

}
