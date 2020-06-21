package com.utn.utnphones.exceptions;

import com.utn.utnphones.repositories.TariffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TariffNotExistsException extends Throwable {
    public TariffNotExistsException(String message){
        super(message);
    }
}
