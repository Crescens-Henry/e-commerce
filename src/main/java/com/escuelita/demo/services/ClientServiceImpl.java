package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateClientRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateClientRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetClientResponse;
import com.escuelita.demo.entities.Client;
import com.escuelita.demo.entities.projections.ClientProjection;
import com.escuelita.demo.repositories.IClientRepository;
import com.escuelita.demo.security.UserDetailsImpl;
import com.escuelita.demo.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService, UserDetailsService {

    @Autowired
    private IClientRepository repository;

    @Override
    public BaseResponse list() {
        List<GetClientResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Clients have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetClientResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Client has been found here")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse getClientByEmail(String email) {
        List<GetClientResponse> response = from(email);
        return BaseResponse.builder()
                .data(response)
                .message("Client has been found by selected email")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Client findClientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client has not been found"));
    }

    @Override
    public BaseResponse create(CreateClientRequest request) {
        Client client = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(client)))
                .message("Client created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateClientRequest request) {
        Client client = findClientById(id);
        GetClientResponse response = from(update(client, request));
        return BaseResponse.builder()
                .data(response)
                .message("Client has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        findClientById(id);
        repository.deleteById(id);
    }

    private Client update(Client client, UpdateClientRequest request) {
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setPhone(request.getPhone());
        return repository.save(client);
    }

    private GetClientResponse from(Client client) {
        GetClientResponse response = new GetClientResponse();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setEmail(client.getEmail());
        response.setLastName(client.getLastName());
        response.setPhone(client.getPhone());
        return response;
    }

    private GetClientResponse from(ClientProjection client) {
        GetClientResponse response = new GetClientResponse();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setEmail(client.getEmail());
        response.setLastName(client.getLast_name());
        response.setPhone(client.getPhone());
        return response;
    }

    private GetClientResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Client doesn't exist"));
    }

    private List<GetClientResponse> from(String email) {
        return repository.findClientByEmail(email)
                .stream().map(this::from)
                .collect(Collectors.toList());
    }

    private Client from(CreateClientRequest request) {
        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        client.setName(request.getName());
        client.setLastName(request.getLastName());
        client.setPhone(request.getPhone());
        return client;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = repository.findOneByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("the client with email " + email + " does not exist."));
        return new UserDetailsImpl(client);
    }

}
