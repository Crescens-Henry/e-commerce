package com.escuelita.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.OrderResponse;
import com.escuelita.demo.controllers.dtos.responses.ProductResponse;
import com.escuelita.demo.entities.projections.OrderProjection;
import com.escuelita.demo.entities.projections.ProductProjection;
import com.escuelita.demo.repositories.IProductOrderRepository;
import com.escuelita.demo.services.interfaces.IProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private IProductOrderRepository repository;

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
        List<OrderResponse> response = orders.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Orders By Product Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private OrderResponse from(OrderProjection order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setDate(order.getDate());

        // * compuesto
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
        return response;
    }
}
