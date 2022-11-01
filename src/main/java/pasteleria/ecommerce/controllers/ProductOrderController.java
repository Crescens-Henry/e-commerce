package pasteleria.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.OrderResponse;
import pasteleria.ecommerce.services.interfaces.IProductOrderService;

@RestController
@RequestMapping("product-order")
public class ProductOrderController {
    @Autowired
    private IProductOrderService service;

    @GetMapping("product/order/{orderId}")
    public ResponseEntity<BaseResponse> listAllProductsByOrderId(@PathVariable Long orderId) {
        BaseResponse baseResponse = service.listAllProductsByOrderId(orderId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("orders/product/{productId}")
    public List<OrderResponse> listAllOrdersByProductId(@PathVariable Long productId) {
        return service.listAllOrdersByProductId(productId);
    }
}
