package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {
    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){
        ZooErrorResponse errorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(), zooException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, zooException.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleGeneric(Exception zooException){
        ZooErrorResponse errorResponse = new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), zooException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
