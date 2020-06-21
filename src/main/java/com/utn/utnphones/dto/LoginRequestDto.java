package com.utn.utnphones.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    String username;
    String password;

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
