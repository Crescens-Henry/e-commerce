package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    BaseResponse upload(MultipartFile multipartFile);
}
