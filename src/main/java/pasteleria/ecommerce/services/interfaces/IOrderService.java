package pasteleria.ecommerce.services.interfaces;

import java.util.List;

import pasteleria.ecommerce.controllers.dtos.requests.CreateOrderRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateOrderRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetOrderResponse;
import pasteleria.ecommerce.entities.Order;

public interface IOrderService {

    GetOrderResponse get(Long id);

    List<GetOrderResponse> list();

    void delete(Long id);

    BaseResponse create(CreateOrderRequest request);

    GetOrderResponse update(Long id, UpdateOrderRequest request);

    Order findOneAndEnsureExist(Long id);

    Order save(Order client);
}
