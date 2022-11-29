package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateAddressRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateAddressRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetAddressResponse;
import com.escuelita.demo.entities.Address;
import com.escuelita.demo.entities.Client;

import com.escuelita.demo.repositories.IAddressRepository;
import com.escuelita.demo.services.interfaces.IAddressService;
import com.escuelita.demo.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    IAddressRepository repository;

    @Autowired
    IClientService clientService;

    @Override
    public BaseResponse list() {
        List<GetAddressResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Addresses have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetAddressResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Address has been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Address findAddressById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Address has never been declared"));
    }

    @Override
    public BaseResponse create(CreateAddressRequest request) {
        Address address = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(address)))
                .message("Address created and saved correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateAddressRequest request) {
        Address address = findAddressById(id);
        GetAddressResponse response = from(update(address, request));
        return BaseResponse.builder()
                .data(response)
                .message("Address has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Address update(Address address, UpdateAddressRequest request) {
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setZipCode(request.getZipCode());
        return repository.save(address);
    }

    private GetAddressResponse from(Address address) {
        GetAddressResponse response = new GetAddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setHouseNumber(address.getHouseNumber());
        response.setZipCode(address.getZipCode());
        response.setClientId(address.getClient().getId());
        return response;
    }

    private GetAddressResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Address hasn't been found"));
    }

    private Address from(CreateAddressRequest request) {
        Address address = new Address();
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setZipCode(request.getZipCode());

        Client client = clientService.findClientById(request.getClientId());

        address.setClient(client);

        return address;
    }

    @Override
    public BaseResponse listAllAddressByClientId(Long clientId) {
        List<Address> address = repository.listAllAddressesByClientId(clientId);

        List<GetAddressResponse> response = address.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Address By Client Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }
}
