package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.escuelita.demo.services.interfaces.IFileService;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private IFileService service;

    @PostMapping
    public ResponseEntity<BaseResponse> upload(@RequestParam MultipartFile file) {
        BaseResponse baseResponse = service.upload(file);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
