package com.utn.utnphones.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer idLocality;

    private String name;

    private String lastname;

    private String username;

    private String password;

    private String userType;

    private Boolean active;
}
