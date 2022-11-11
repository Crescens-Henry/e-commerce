package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateProductOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IProductOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product-order")
public class ProductOrderController {
    @Autowired
    private IProductOrderService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateProductOrderRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("product/order/{orderId}")
    public ResponseEntity<BaseResponse> listAllProductsByOrderId(@PathVariable Long orderId) {
        BaseResponse baseResponse = service.listAllProductsByOrderId(orderId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("order/product/{productId}")
    public ResponseEntity<BaseResponse> listAllOrdersByProductId(@PathVariable Long productId) {
        BaseResponse baseResponse = service.listAllOrdersByProductId(productId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
