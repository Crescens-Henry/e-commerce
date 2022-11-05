package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

public interface IProductOrderService {

    BaseResponse listAllProductsByOrderId(Long orderId);

    BaseResponse listAllOrdersByProductId(Long productId);
}
