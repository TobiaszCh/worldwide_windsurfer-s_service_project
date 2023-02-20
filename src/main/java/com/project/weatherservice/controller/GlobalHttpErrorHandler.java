package com.project.weatherservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AllNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException() {
        return new ResponseEntity<>("This response no exist. Change date :)", HttpStatus.BAD_REQUEST);

    }
}
