package pasteleria.ecommerce.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBillResponse {
    private Double amount;
    private Double iva;
    private String date;
}
