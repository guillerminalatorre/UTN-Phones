package com.utn.utnphones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneLineDto {
    Integer idUser;
    String phoneNumber;
    Integer idLineType;
    String lineStatus;
}
