package pasteleria.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.OrderResponse;
import pasteleria.ecommerce.controllers.dtos.responses.ProductResponse;
import pasteleria.ecommerce.entities.projections.OrderProjection;
import pasteleria.ecommerce.entities.projections.ProductProjection;
import pasteleria.ecommerce.repositories.IProductOrderRepository;
import pasteleria.ecommerce.services.interfaces.IProductOrderService;

@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private IProductOrderRepository repository;

    private OrderResponse from(OrderProjection order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setDate(order.getDate());
        return response;
    }

    private ProductResponse from(ProductProjection product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setCode(product.getCode());
        response.setDescription(product.getDescription());
        response.setQuantity(product.getQuantity());
        response.setOrderDate(product.getOrderDate());
        return response;
    }

    @Override
    public BaseResponse listAllProductsByOrderId(Long orderId) {
        List<ProductProjection> products = repository.listAllProductsByOrderId(orderId);
        List<ProductResponse> response = products.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Product list by order id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public List<OrderResponse> listAllOrdersByProductId(Long productId) {
        List<OrderProjection> orders = repository.listAllOrdersByProductId(productId);
        return orders.stream().map(this::from).collect(Collectors.toList());
    }

}
