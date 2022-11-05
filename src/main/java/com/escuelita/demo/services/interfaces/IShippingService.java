package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateShippingRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateShippingRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Shipping;

public interface IShippingService {

    BaseResponse list();

    BaseResponse get(Long id);

    Shipping findShippingById(Long id);

    BaseResponse create(CreateShippingRequest request);

    BaseResponse update(Long id, UpdateShippingRequest request);

    void delete(Long id);
}
