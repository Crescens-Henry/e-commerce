package com.escuelita.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.escuelita.demo.controllers.dtos.requests.CreateProductOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetOrderResponse;
import com.escuelita.demo.controllers.dtos.responses.GetProductOrderResponse;
import com.escuelita.demo.controllers.dtos.responses.OrderResponse;
import com.escuelita.demo.controllers.dtos.responses.ProductResponse;
import com.escuelita.demo.entities.Order;
import com.escuelita.demo.entities.Product;
import com.escuelita.demo.entities.pivots.ProductOrder;
import com.escuelita.demo.entities.projections.OrderProjection;
import com.escuelita.demo.entities.projections.ProductProjection;
import com.escuelita.demo.repositories.IProductOrderRepository;
import com.escuelita.demo.services.interfaces.IOrderService;
import com.escuelita.demo.services.interfaces.IProductOrderService;
import com.escuelita.demo.services.interfaces.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private IProductOrderRepository repository;

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;

    @Override
    public BaseResponse create(CreateProductOrderRequest request) {
        ProductOrder productOrder = from(request);
        GetProductOrderResponse response = from(repository.save(productOrder));
        return BaseResponse.builder()
                .data(response)
                .message("Relation between Product and Order has been created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    private GetProductOrderResponse from(ProductOrder productOrder) {
        GetProductOrderResponse response = new GetProductOrderResponse();
        response.setId(productOrder.getId());
        response.setOrder(from(productOrder.getOrder()));
        response.setProduct(from(productOrder.getProduct()));
        return response;

    }

    private ProductOrder from(CreateProductOrderRequest request) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrder(orderService.findOrderById(request.getOrderId()));
        productOrder.setProduct(productService.findProductbyId(request.getProductId()));
        return productOrder;
    }

    private OrderResponse from(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setDate(order.getDate());
        response.setClientId(order.getClient().getId());
        response.setShippingId(order.getShipping().getId());
        response.setBillId(order.getBill().getId());
        response.setStatusOrderId(order.getStatusOrder().getId());
        return response;
    }

    private ProductResponse from(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setCakePicture(product.getCakePicture());
        return response;

    }

    @Override
    public BaseResponse listAllProductsByOrderId(Long orderId) {
        List<ProductProjection> products = repository.listAllProductsByOrderId(orderId);
        List<ProductResponse> response = products.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Products By Order Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllOrdersByProductId(Long productId) {
        List<OrderProjection> orders = repository.listAllOrdersByProductId(productId);
        List<GetOrderResponse> response = orders.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Orders By Product Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private GetOrderResponse from(OrderProjection order) {
        GetOrderResponse response = new GetOrderResponse();
        response.setId(order.getId());
        response.setDate(order.getDate());
        response.setProductName(order.getProductName());
        return response;
    }

    private ProductResponse from(ProductProjection product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setQuantity(product.getQuantity());
        response.setCakePicture(product.getCake_Picture());
        return response;
    }

}
