package com.dholubeu.passengerservice.web.controller;

import com.dholubeu.passengerservice.domain.exception.PasswordMismatchException;
import com.dholubeu.passengerservice.domain.exception.ResourceAlreadyExistsException;
import com.dholubeu.passengerservice.domain.exception.ResourceDoesNotExistException;
import com.dholubeu.passengerservice.web.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        log.error(ex.getMessage());
        return new ResponseDto(errors);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlePasswordMismatchException(PasswordMismatchException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto handleResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

}