package com.utn.utnphones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CallByLocalityToNotFound extends RuntimeException {
    public CallByLocalityToNotFound(String phoneNumberTo){
        super("No existe llamada realizada al numero: " + phoneNumberTo);

        System.out.println(this.getMessage());
    }
}