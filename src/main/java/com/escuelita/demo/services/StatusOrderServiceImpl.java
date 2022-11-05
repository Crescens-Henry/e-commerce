package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateStatusOrderRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateStatusOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetStatusOrderResponse;
import com.escuelita.demo.entities.StatusOrder;
import com.escuelita.demo.repositories.IStatusOrderRepository;
import com.escuelita.demo.services.interfaces.IStatusOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusOrderServiceImpl implements IStatusOrderService {

    @Autowired
    IStatusOrderRepository repository;

    @Override
    public BaseResponse list() {
        List<GetStatusOrderResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Status Orders have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetStatusOrderResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Status Order has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public StatusOrder findStatusOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Status Order has not been found"));

    }

    @Override
    public BaseResponse create(CreateStatusOrderRequest request) {
        StatusOrder statusOrder = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(statusOrder))
                .message("Status Order created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateStatusOrderRequest request) {
        StatusOrder statusOrder = findStatusOrderById(id);
        GetStatusOrderResponse response = from(update(statusOrder, request));
        return BaseResponse.builder()
                .data(response)
                .message("Status Order updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        findStatusOrderById(id);
        repository.deleteById(id);
    }

    private StatusOrder update(StatusOrder statusOrder, UpdateStatusOrderRequest request){
        statusOrder.setStatus(request.getStatus());
        statusOrder.setDateOrderEnded(request.getDateOrderEnded());
        return repository.save(statusOrder);
    }

    private GetStatusOrderResponse from(StatusOrder statusOrder){
        GetStatusOrderResponse response = new GetStatusOrderResponse();
        response.setId(statusOrder.getId());
        response.setStatus(statusOrder.getStatus());
        response.setDateOrderReceived(statusOrder.getDateOrderReceived());
        response.setDateOrderEnded(statusOrder.getDateOrderEnded());
        return response;
    }

    private GetStatusOrderResponse from(Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow( () -> new RuntimeException("Status Order doesn't exit"));
    }

    private StatusOrder from(CreateStatusOrderRequest request){
        StatusOrder statusOrder = new StatusOrder();
        statusOrder.setStatus(request.getStatus());
        statusOrder.setDateOrderReceived(request.getDateOrderReceived());
        statusOrder.setDateOrderEnded(request.getDateOrderEnded());
        return statusOrder;
    }
}
