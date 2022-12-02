package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateClientRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateClientRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Client;

public interface IClientService {

    BaseResponse list();

    BaseResponse get(Long id);

    BaseResponse getClientByEmail(String email);

    Client findClientById(Long id);

    BaseResponse create(CreateClientRequest request);

    BaseResponse update(Long id, UpdateClientRequest request);

    void delete(Long id);
}
