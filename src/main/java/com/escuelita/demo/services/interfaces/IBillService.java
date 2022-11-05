package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateBillRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBillRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Bill;

public interface IBillService {

   BaseResponse list();

    BaseResponse get(Long id);

    Bill findBillById(Long id);

    BaseResponse create(CreateBillRequest request);

    BaseResponse update(Long id, UpdateBillRequest request);

    void delete(Long id);

}
