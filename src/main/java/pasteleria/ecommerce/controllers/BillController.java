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

import pasteleria.ecommerce.controllers.dtos.requests.CreateBillRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateBillRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetBillResponse;
import pasteleria.ecommerce.services.interfaces.IBillService;

@RestController
@RequestMapping("bill")
public class BillController {
    @Autowired
    private IBillService service;

    @GetMapping
    public List<GetBillResponse> list() {
        return service.list();
    }

    @GetMapping("{id}")
    public GetBillResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateBillRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public GetBillResponse update(@PathVariable Long id, @RequestBody UpdateBillRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
