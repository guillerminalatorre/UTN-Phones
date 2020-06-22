package com.utn.utnphones.dto;

import lombok.Data;

@Data
public class PhoneLineDto {
    Integer idUser;
    String phoneNumber;
    Integer idLineType;
    String lineStatus;
}
