package pasteleria.ecommerce.controllers.dtos.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBillRequest {
    @NotNull
    private Double amount;
    @NotNull
    private Double iva;
    @NotBlank
    private String date;
}
