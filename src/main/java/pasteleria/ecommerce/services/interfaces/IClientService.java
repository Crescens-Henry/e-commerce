package pasteleria.ecommerce.services.interfaces;

import java.util.List;

import pasteleria.ecommerce.controllers.dtos.requests.CreateClientRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateClientRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetClientResponse;
import pasteleria.ecommerce.entities.Client;

public interface IClientService {
    GetClientResponse get(Long id);

    List<GetClientResponse> list();

    void delete(Long id);

    BaseResponse create(CreateClientRequest request);

    GetClientResponse update(Long id, UpdateClientRequest request);

    Client findOneAndEnsureExist(Long id);

    Client save(Client client);
}
