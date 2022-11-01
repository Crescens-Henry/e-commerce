package pasteleria.ecommerce.controllers.dtos.requests;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private Long id;
    @NotBlank
    private String date;
}
