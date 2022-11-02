package pasteleria.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pasteleria.ecommerce.controllers.dtos.requests.CreateClientRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateClientRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetClientResponse;
import pasteleria.ecommerce.entities.Client;
import pasteleria.ecommerce.repositories.IClientRepository;
import pasteleria.ecommerce.services.interfaces.IClientService;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private IClientRepository repository;

    @Override
    public GetClientResponse get(Long id) {
        return from(id);
    }

    @Override
    public List<GetClientResponse> list() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateClientRequest request) {
        Client client = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(client)))
                .message("Client created correctly")
                .success(Boolean.TRUE).httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public GetClientResponse update(Long id, UpdateClientRequest request) {
        Client client = findOneAndEnsureExist(id);
        client = update(client, request);
        return from(client);
    }

    @Override
    public Client findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("The client does not exist"));
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    private Client update(Client client, UpdateClientRequest request) {
        client.setName(request.getName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        client.setPassword(request.getPassword());
        return repository.save(client);
    }

    private Client from(CreateClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        client.setPassword(request.getPassword());

        return client;
    }

    private GetClientResponse from(Client client) {
        GetClientResponse response = new GetClientResponse();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setLastName(client.getLastName());
        response.setEmail(client.getEmail());
        response.setPhone(client.getPhone());
        return response;
    }

    private GetClientResponse from(Long idClient) {
        return repository.findById(idClient).map(this::from).orElseThrow(() -> new RuntimeException("estas mal"));
    }
}
