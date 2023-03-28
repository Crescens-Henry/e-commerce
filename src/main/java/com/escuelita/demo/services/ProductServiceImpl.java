package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateProductRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProductRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetProductResponse;
import com.escuelita.demo.entities.Product;
import com.escuelita.demo.repositories.IProductRepository;
import com.escuelita.demo.services.interfaces.IFileService;
import com.escuelita.demo.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private IFileService fileService;

    @Override
    public BaseResponse list() {
        List<GetProductResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Products have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetProductResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Product has been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Product findProductbyId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

    @Override
    public BaseResponse create(CreateProductRequest request) {
        Product product = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(product))
                .message("Product added correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateProductRequest request) {
        Product product = findProductbyId(id);
        GetProductResponse response = from(update(product, request));
        return BaseResponse.builder()
                .data(response)
                .message("Product has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Product update(Product product, UpdateProductRequest request) {
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCakePicture(request.getCakePicture());
        return repository.save(product);
    }

    private GetProductResponse from(Product product) {
        GetProductResponse response = new GetProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setType(product.getType());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setQuantity(product.getQuantity());
        response.setCakePicture(product.getCakePicture());
        return response;
    }

    private GetProductResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Product has not been found"));
    }

    private Product from(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setType(request.getType());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setCakePicture(request.getCakePicture());
        return product;
    }

    @Override
    public BaseResponse uploadCakePhoto(MultipartFile file) {
        BaseResponse cakePicture = fileService.upload(file);
        return BaseResponse.builder()
                .data(cakePicture)
                .message("The Cake Photo uploaded correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }
}
