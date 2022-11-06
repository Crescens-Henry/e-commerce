package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateStatusOrderRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateStatusOrderRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IStatusOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("statusOrder")
public class StatusOrderController {

    @Autowired
    private IStatusOrderService service;

    @GetMapping
    private ResponseEntity<BaseResponse> list() {
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    private ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    private ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateStatusOrderRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    private ResponseEntity<BaseResponse> update(@PathVariable Long id,
            @RequestBody @Valid UpdateStatusOrderRequest request) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
