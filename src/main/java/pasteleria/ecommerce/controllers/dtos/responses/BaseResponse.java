package pasteleria.ecommerce.controllers.dtos.responses;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse {
    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
}
