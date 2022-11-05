package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetClientResponse {

    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String phone;
}
