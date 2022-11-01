package pasteleria.ecommerce.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private String code;
    private String description;
    private Integer quantity;
    private String orderDate;
}
