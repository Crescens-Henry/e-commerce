package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateShippingRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateShippingRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetShippingResponse;
import com.escuelita.demo.entities.Shipping;
import com.escuelita.demo.repositories.IShippingRepository;
import com.escuelita.demo.services.interfaces.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements  IShippingService{

    @Autowired
    IShippingRepository repository;

    @Override
    public BaseResponse list() {
        List<GetShippingResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Shippings have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetShippingResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Shipping has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Shipping findShippingById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Shipping has not been found"));
    }

    @Override
    public BaseResponse create(CreateShippingRequest request) {
        Shipping shipping = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(shipping))
                .message("Shipping generated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateShippingRequest request) {
        Shipping shipping = findShippingById(id);
        GetShippingResponse response = from(update(shipping, request));
        return BaseResponse.builder()
                .data(response)
                .message("Shipping has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        findShippingById(id);
        repository.deleteById(id);
    }

    private Shipping update(Shipping shipping, UpdateShippingRequest request){
        shipping.setDateExit(request.getDateExit());
        return repository.save(shipping);
    }

    private GetShippingResponse from(Shipping shipping){
        GetShippingResponse response = new GetShippingResponse();
        response.setId(shipping.getId());
        response.setDataExit(shipping.getDateExit());
        response.setDataReceived(shipping.getDateReceived());
        return response;
    }

    private GetShippingResponse from(Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow( () -> new RuntimeException("Shipping doesn't exit"));
    }

    private Shipping from(CreateShippingRequest request){
        Shipping shipping = new Shipping();
        shipping.setDateExit(request.getDateExit());
        shipping.setDateReceived(request.getDateReceived());
        return shipping;
    }
}
