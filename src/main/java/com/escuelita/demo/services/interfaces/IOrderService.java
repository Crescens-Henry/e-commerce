package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateOrderRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Order;

public interface IOrderService {

    BaseResponse list();

    BaseResponse get(Long id);

    Order findOrderById(Long id);

    BaseResponse create(CreateOrderRequest request);

    BaseResponse update(Long id, UpdateOrderRequest request);

    void delete(Long id);

}
