package com.utn.utnphones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneLineDto {
    Integer idUser;
    String phoneNumber;
    Integer idLineType;
    String lineStatus;
}
