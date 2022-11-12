package com.escuelita.demo.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.escuelita.demo.controllers.dtos.requests.CreateProductRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProductRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Product;

public interface IProductService {

    BaseResponse list();

    BaseResponse get(Long id);

    Product findProductbyId(Long id);

    BaseResponse create(CreateProductRequest request);

    BaseResponse update(Long id, UpdateProductRequest request);

    void delete(Long id);

    BaseResponse uploadCakePhoto(MultipartFile file);
}
