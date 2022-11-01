package pasteleria.ecommerce.controllers.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotNull
    private Number phone;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;

    private Long Orderid;
}
