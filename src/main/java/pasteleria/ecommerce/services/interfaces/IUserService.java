package pasteleria.ecommerce.services.interfaces;

import java.util.List;
import pasteleria.ecommerce.controllers.dtos.requests.CreateUserRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateUserRequest;
import pasteleria.ecommerce.controllers.dtos.responses.GetUserResponse;

public interface IUserService {
    GetUserResponse get(Long id);

    List<GetUserResponse> list();

    void delete(Long id);

    GetUserResponse create(CreateUserRequest request);

    GetUserResponse update(Long id, UpdateUserRequest request);
}
