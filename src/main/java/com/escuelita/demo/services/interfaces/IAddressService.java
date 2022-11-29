package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateAddressRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateAddressRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Address;

public interface IAddressService {

    BaseResponse list();

    BaseResponse get(Long id);

    BaseResponse listAllAddressByClientId(Long clientId);

    Address findAddressById(Long id);

    BaseResponse create(CreateAddressRequest request);

    BaseResponse update(Long id, UpdateAddressRequest request);

    void delete(Long id);
}
