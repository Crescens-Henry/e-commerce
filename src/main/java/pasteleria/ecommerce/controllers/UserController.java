package pasteleria.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pasteleria.ecommerce.controllers.dtos.requests.CreateUserRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateUserRequest;
import pasteleria.ecommerce.controllers.dtos.responses.GetUserResponse;
import pasteleria.ecommerce.services.interfaces.IUserService;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping
    public List<GetUserResponse> list() {
        return service.list();
    }

    @GetMapping("{id}")
    public GetUserResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public GetUserResponse create(@RequestBody CreateUserRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetUserResponse update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
