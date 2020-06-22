package com.utn.utnphones.dto;

import lombok.Data;

@Data
public class NewUserDto {

    private Integer idLocality;

    private String name;

    private String lastname;

    private String username;

    private String idNumber;

    private String userType;

    private Boolean active;
}
