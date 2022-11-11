package com.escuelita.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.escuelita.demo.controllers.dtos.requests.CreateOrderRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.OrderResponse;
import com.escuelita.demo.entities.*;
import com.escuelita.demo.repositories.IOrderRepository;
import com.escuelita.demo.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository repository;

    @Autowired
    private IClientService clientService;

    @Autowired
    private IShippingService shippingService;

    @Autowired
    private IBillService billService;

    @Autowired
    private IStatusOrderService statusOrderService;

    @Override
    public BaseResponse list() {
        List<OrderResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Orders have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        OrderResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Order has been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Order findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order does not exist"));
    }

    @Override
    public BaseResponse create(CreateOrderRequest request) {
        Order order = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(order)))
                .message("Order created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateOrderRequest request) {
        Order order = findOrderById(id);
        OrderResponse response = from(update(order, request));
        return BaseResponse.builder()
                .data(response)
                .message("Order has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Order update(Order order, UpdateOrderRequest request) {
        order.setDate(request.getDate());
        return repository.save(order);
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

    private OrderResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Order doesn't exit"));
    }

    private Order from(CreateOrderRequest request) {
        Order order = new Order();
        order.setDate(request.getDate());

        Client client = clientService.findClientById(request.getClientId());

        Shipping shipping = shippingService.findShippingById(request.getShippingId());

        Bill bill = billService.findBillById(request.getBillId());

        StatusOrder statusOrder = statusOrderService.findStatusOrderById(request.getStatusOrderId());

        order.setClient(client);
        order.setShipping(shipping);
        order.setBill(bill);
        order.setStatusOrder(statusOrder);
        return order;
    }
}
