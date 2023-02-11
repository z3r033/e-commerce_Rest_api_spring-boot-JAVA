/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.exceptions;


import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.hayatbackend.ui.model.response.ErrorMessage;

/**
 *
 * @author saad
 */
@ControllerAdvice()
public class AppExceptionHandler {
    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException (UserServiceException ex , WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage/*ex.getMessage()*/, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException (Exception ex , WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage/*ex.getMessage()*/, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
