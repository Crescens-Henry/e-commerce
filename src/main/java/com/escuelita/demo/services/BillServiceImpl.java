package com.escuelita.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.escuelita.demo.controllers.dtos.requests.CreateBillRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBillRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetBillResponse;
import com.escuelita.demo.entities.Bill;
import com.escuelita.demo.repositories.IBillRepository;
import com.escuelita.demo.services.interfaces.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements IBillService {

    @Autowired
    private IBillRepository repository;

    @Override
    public BaseResponse list() {
        List<GetBillResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Bills have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetBillResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Bill has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Bill findBillById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Bill has not been found"));
    }

    @Override
    public BaseResponse create(CreateBillRequest request) {
        Bill bill = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(bill))
                .message("Bill created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateBillRequest request) {
        Bill bill = findBillById(id);
        GetBillResponse response = from(update(bill, request));
        return BaseResponse.builder()
                .data(response)
                .message("Bill has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Bill update(Bill bill, UpdateBillRequest request) {
        bill.setAmount(request.getAmount());
        bill.setDate(request.getDate());
        return repository.save(bill);
    }

    private GetBillResponse from(Bill bill) {
        GetBillResponse response = new GetBillResponse();
        response.setId(bill.getId());
        response.setAmount(bill.getAmount());
        response.setIva(bill.getIva());
        response.setDate(bill.getDate());
        return response;
    }

    private GetBillResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Bill doesn't exit"));
    }


    private Bill from(CreateBillRequest request) {
        Bill bill = new Bill();
        bill.setAmount(request.getAmount());
        bill.setIva(request.getIva());
        bill.setDate(request.getDate());
        return bill;
    }

}
