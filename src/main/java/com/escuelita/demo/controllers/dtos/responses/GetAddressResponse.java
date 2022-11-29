package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAddressResponse {

    private Long id;
    private String street;
    private String houseNumber;
    private Integer zipCode;
    private Long clientId;
}
