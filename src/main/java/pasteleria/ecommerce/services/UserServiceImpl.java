package pasteleria.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pasteleria.ecommerce.controllers.dtos.requests.CreateUserRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateUserRequest;
import pasteleria.ecommerce.controllers.dtos.responses.GetUserResponse;
import pasteleria.ecommerce.entities.User;
import pasteleria.ecommerce.repositories.IUserRepository;
import pasteleria.ecommerce.services.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        return response;
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    private GetUserResponse from(Long id) {
        return repository
                .findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Error de from id"));
    }

    @Override
    public GetUserResponse get(Long id) {
        return from(id);
    }

    @Override
    public List<GetUserResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public GetUserResponse create(CreateUserRequest request) {
        User user = from(request);
        return from(repository.save(user));
    }

    private User update(User user, UpdateUserRequest request) {
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return repository.save(user);
    }

    @Override
    public GetUserResponse update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Error de update"));
        user = update(user, request);
        return from(user);
    }
}
