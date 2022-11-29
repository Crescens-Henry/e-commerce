package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreatePaymentRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePaymentRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetPaymentResponse;
import com.escuelita.demo.entities.Client;
import com.escuelita.demo.entities.Payment;
import com.escuelita.demo.repositories.IPaymentRepository;
import com.escuelita.demo.services.interfaces.IClientService;
import com.escuelita.demo.services.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private IPaymentRepository repository;

    @Autowired
    private IClientService clientService;

    @Override
    public BaseResponse list() {
        List<GetPaymentResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Payments have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetPaymentResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Payment has been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Payment findPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment has never been declared"));
    }

    @Override
    public BaseResponse create(CreatePaymentRequest request) {
        Payment payment = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(payment)))
                .message("Payment created and saved correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdatePaymentRequest request) {
        Payment payment = findPaymentById(id);
        GetPaymentResponse response = from(update(payment, request));
        return BaseResponse.builder()
                .data(response)
                .message("Payment has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        findPaymentById(id);
        repository.deleteById(id);
    }

    private Payment update(Payment payment, UpdatePaymentRequest request) {
        payment.setCardNumber(request.getCardNumber());
        payment.setDateExpiry(request.getDateExpiry());
        payment.setCardHolder(request.getCardHolder());
        payment.setCardIssuer(request.getCardIssuer());
        payment.setCvv(request.getCvv());
        return repository.save(payment);
    }

    private GetPaymentResponse from(Payment payment) {
        GetPaymentResponse response = new GetPaymentResponse();
        response.setId(payment.getId());
        response.setCardHolder(payment.getCardHolder());
        response.setCardNumber(payment.getCardNumber());
        response.setClientId(payment.getClient().getId());
        return response;
    }

    private GetPaymentResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Payment doesn't exist"));
    }

    private Payment from(CreatePaymentRequest request) {
        Payment payment = new Payment();
        payment.setCardNumber(request.getCardNumber());
        payment.setDateExpiry(request.getDateExpiry());
        payment.setCardHolder(request.getCardHolder());
        payment.setCardIssuer(request.getCardIssuer());
        payment.setCvv(request.getCvv());

        Client client = clientService.findClientById(request.getClientId());

        payment.setClient(client);

        return payment;
    }

    @Override
    public BaseResponse listAllPaymentByClientId(Long clientId) {
        List<Payment> payments = repository.listAllPaymentsByClientId(clientId);
        List<GetPaymentResponse> response = payments.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Payment By Client Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }
}
