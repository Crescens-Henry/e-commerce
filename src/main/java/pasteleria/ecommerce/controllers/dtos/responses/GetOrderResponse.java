package pasteleria.ecommerce.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderResponse {
    private Long id;
    private String date;
}
