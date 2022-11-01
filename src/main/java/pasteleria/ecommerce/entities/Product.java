package pasteleria.ecommerce.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import pasteleria.ecommerce.entities.pivots.ProductOrder;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String code;
    private String description;
    private Integer quantity;
    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders;
}
