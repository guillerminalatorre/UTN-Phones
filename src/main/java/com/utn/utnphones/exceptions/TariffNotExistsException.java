package com.utn.utnphones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TariffNotExistsException extends RuntimeException {
    public TariffNotExistsException(Integer idTariff){
        super("No existe Tarifa con ese Id : " + idTariff);

        System.out.println(this.getMessage());
    }
}
