package pasteleria.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pasteleria.ecommerce.controllers.dtos.requests.CreateClientRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateClientRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetClientResponse;
import pasteleria.ecommerce.services.interfaces.IClientService;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping
    public List<GetClientResponse> list() {
        return service.list();
    }

    @GetMapping("{id}")
    public GetClientResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateClientRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public GetClientResponse update(@PathVariable Long id, @RequestBody UpdateClientRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
