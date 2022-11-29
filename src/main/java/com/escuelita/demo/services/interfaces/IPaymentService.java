package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreatePaymentRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePaymentRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Payment;

public interface IPaymentService {

    BaseResponse list();

    BaseResponse get(Long id);

    BaseResponse listAllPaymentByClientId(Long clientId);

    Payment findPaymentById(Long id);

    BaseResponse create(CreatePaymentRequest request);

    BaseResponse update(Long id, UpdatePaymentRequest request);

    void delete(Long id);
}
