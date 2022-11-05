package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateStatusOrderRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateStatusOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.StatusOrder;

public interface IStatusOrderService {

    BaseResponse list();

    BaseResponse get(Long id);

    StatusOrder findStatusOrderById(Long id);

    BaseResponse create(CreateStatusOrderRequest request);

    BaseResponse update(Long id, UpdateStatusOrderRequest request);

    void delete(Long id);
}
