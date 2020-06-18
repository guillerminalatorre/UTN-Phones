package com.utn.utnphones.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class UserDto {

    private Integer idLocality;

    private String name;

    private String lastname;

    private String username;

    private String idNumber;

    private String password;

    private String userType;

    private Boolean active;
}
