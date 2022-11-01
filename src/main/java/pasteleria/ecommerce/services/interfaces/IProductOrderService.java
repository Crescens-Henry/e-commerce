package pasteleria.ecommerce.services.interfaces;

import java.util.List;

import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.OrderResponse;

public interface IProductOrderService {

    BaseResponse listAllProductsByOrderId(Long orderId);

    List<OrderResponse> listAllOrdersByProductId(Long productId);
}
