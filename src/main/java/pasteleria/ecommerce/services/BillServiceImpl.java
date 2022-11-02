package pasteleria.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pasteleria.ecommerce.controllers.dtos.requests.CreateBillRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateBillRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetBillResponse;
import pasteleria.ecommerce.entities.Bill;
import pasteleria.ecommerce.repositories.IBillRepository;
import pasteleria.ecommerce.services.interfaces.IBillService;

@Service
public class BillServiceImpl implements IBillService {

    @Autowired
    private IBillRepository repository;

    @Override
    public GetBillResponse get(Long id) {
        return from(id);
    }

    @Override
    public List<GetBillResponse> list() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateBillRequest request) {
        Bill bill = from(request);
        return BaseResponse.builder().data(from(repository.save(bill))).message("Bill created correctly")
                .success(Boolean.TRUE).httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public GetBillResponse update(Long id, UpdateBillRequest request) {
        Bill bill = findOneAndEnsureExist(id);
        bill = update(bill, request);
        return from(bill);
    }

    @Override
    public Bill findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("The Bill does not exist"));
    }

    @Override
    public Bill save(Bill bill) {
        return repository.save(bill);
    }

    private Bill update(Bill bill, UpdateBillRequest request) {
        bill.setAmount(request.getAmount());
        bill.setIva(request.getIva());
        bill.setDate(request.getDate());
        return repository.save(bill);
    }

    private Bill from(CreateBillRequest request) {
        Bill bill = new Bill();
        bill.setAmount(request.getAmount());
        bill.setIva(request.getIva());
        bill.setDate(request.getDate());
        return bill;
    }

    private GetBillResponse from(Bill bill) {
        GetBillResponse response = new GetBillResponse();
        response.setId(bill.getId());
        response.setAmount(bill.getAmount());
        response.setIva(bill.getIva());
        response.setDate(bill.getDate());
        return response;
    }

    private GetBillResponse from(Long billId) {
        return repository.findById(billId).map(this::from).orElseThrow(() -> new RuntimeException("esta mal"));
    }

}
