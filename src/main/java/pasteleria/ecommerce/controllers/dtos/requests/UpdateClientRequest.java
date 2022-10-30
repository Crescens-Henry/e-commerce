package pasteleria.ecommerce.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientRequest {
    private String name;
    private String lastName;
    private Number phone;
    private String email;
    private String password;
}
