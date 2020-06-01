package com.utn.utnphones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IdLtyFromTariffsNotFoundException extends RuntimeException {

    public IdLtyFromTariffsNotFoundException(Integer idTariff){
        super("El Id de la localidad no corresponde a una existente o no es origen de ninguna tarifa : " + idTariff);

        System.out.println(this.getMessage());
    }

}
