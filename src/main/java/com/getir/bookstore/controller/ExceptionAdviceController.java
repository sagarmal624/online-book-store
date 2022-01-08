package com.getir.bookstore.controller;

import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import com.getir.bookstore.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ResponseDto> bookRecordNotFound(RuntimeException exception, WebRequest request) {
        return new ResponseEntity(ResponseDto.buildError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<ResponseDto> bookRecordNotFound(RecordNotFoundException exception, WebRequest request) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(ErrorResponse.builder().fieldName(exception.getFieldName()).message(exception.getMessage()).build());
        return ResponseEntity.badRequest().body(ResponseDto.buildError(errorResponses));
    }

    @ExceptionHandler(CustomerAuthenticationException.class)
    protected ResponseEntity<ResponseDto> bookRecordNotFound(CustomerAuthenticationException exception, WebRequest request) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(ErrorResponse.builder().fieldName("UserName").message(exception.getMessage()).build());
        errorResponses.add(ErrorResponse.builder().fieldName("Password").message(exception.getMessage()).build());
        return new ResponseEntity(ResponseDto.buildError(errorResponses), HttpStatus.UNAUTHORIZED);
    }

}
