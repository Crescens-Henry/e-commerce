package pasteleria.ecommerce.services.interfaces;

import java.util.List;

import pasteleria.ecommerce.controllers.dtos.requests.CreateBillRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateBillRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetBillResponse;
import pasteleria.ecommerce.entities.Bill;

public interface IBillService {
    GetBillResponse get(Long id);

    List<GetBillResponse> list();

    void delete(Long id);

    BaseResponse create(CreateBillRequest request);

    GetBillResponse update(Long id, UpdateBillRequest request);

    Bill findOneAndEnsureExist(Long id);

    Bill save(Bill bill);
}
