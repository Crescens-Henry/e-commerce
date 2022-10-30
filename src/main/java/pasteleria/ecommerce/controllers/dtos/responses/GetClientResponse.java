package pasteleria.ecommerce.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetClientResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Number phone;
}
