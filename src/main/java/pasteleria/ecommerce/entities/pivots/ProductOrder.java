package pasteleria.ecommerce.entities.pivots;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import pasteleria.ecommerce.entities.Order;
import pasteleria.ecommerce.entities.Product;

@Entity
@Table(name = "products_orders")
@Getter
@Setter
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
}
