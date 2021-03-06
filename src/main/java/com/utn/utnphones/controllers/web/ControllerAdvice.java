package com.utn.utnphones.controllers.web;


import com.utn.utnphones.dto.ErrorResponseDto;
import com.utn.utnphones.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidLoginException.class)
    public ErrorResponseDto handleLoginException(InvalidLoginException exc) {
        return new ErrorResponseDto(1, exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseDto handleValidationException(ValidationException exc) {
        return new ErrorResponseDto(2, exc.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserException.class)
    public ErrorResponseDto handleUserException(UserException exc) {
        return new ErrorResponseDto(3, exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParseException.class)
    public ErrorResponseDto handleParseException() {
        return new ErrorResponseDto(4, "Not valid dates");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PhoneLineNotExistsException.class)
    public ErrorResponseDto handlePhoneLineException(PhoneLineNotExistsException exc) {
        return new ErrorResponseDto(5, exc.getMessage());
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(GoneException.class)
    public ErrorResponseDto handleGoneException(GoneException exc) {
        return new ErrorResponseDto(6, exc.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TariffNotExistsException.class)
    public ErrorResponseDto handleTariffNotExistsException(TariffNotExistsException exc) {
        return new ErrorResponseDto(7, exc.getMessage());
    }

}
