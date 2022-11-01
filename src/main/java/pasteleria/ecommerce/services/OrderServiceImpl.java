package pasteleria.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pasteleria.ecommerce.controllers.dtos.requests.CreateOrderRequest;
import pasteleria.ecommerce.controllers.dtos.requests.UpdateOrderRequest;
import pasteleria.ecommerce.controllers.dtos.responses.BaseResponse;
import pasteleria.ecommerce.controllers.dtos.responses.GetOrderResponse;
import pasteleria.ecommerce.entities.Order;
import pasteleria.ecommerce.repositories.IOrderRepository;
import pasteleria.ecommerce.services.interfaces.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository repository;

    @Override
    public GetOrderResponse get(Long id) {
        return from(id);
    }

    @Override
    public List<GetOrderResponse> list() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateOrderRequest request) {
        Order order = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(order)))
                .message("Order created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public GetOrderResponse update(Long id, UpdateOrderRequest request) {
        Order order = findOneAndEnsureExist(id);
        order = update(order, request);
        return from(order);
    }

    @Override
    public Order findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("The Order does not exist"));
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    private Order update(Order order, UpdateOrderRequest request) {
        order.setDate(request.getDate());
        return repository.save(order);
    }

    private Order from(CreateOrderRequest request) {
        Order order = new Order();
        order.setDate(request.getDate());
        return order;
    }

    private GetOrderResponse from(Order order) {
        GetOrderResponse response = new GetOrderResponse();
        response.setId(order.getId());
        response.setDate(order.getDate());
        return response;
    }

    private GetOrderResponse from(Long idOrder) {
        return repository.findById(idOrder).map(this::from).orElseThrow(() -> new RuntimeException("estas mal"));
    }
}
