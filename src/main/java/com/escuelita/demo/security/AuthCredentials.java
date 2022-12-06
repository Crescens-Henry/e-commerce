package com.escuelita.demo.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentials {
    private String email;
    private String password;
}
