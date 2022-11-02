package pasteleria.ecommerce.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBillRequest {
    private Long id;
    private Double amount;
    private Double iva;
    private String date;
}
