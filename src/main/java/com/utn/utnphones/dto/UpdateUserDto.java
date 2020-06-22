package com.utn.utnphones.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private Integer idLocality;

    private String name;

    private String lastname;

    private String userType;
}

