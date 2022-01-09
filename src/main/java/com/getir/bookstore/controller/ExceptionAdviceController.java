package com.getir.bookstore.controller;

import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import com.getir.bookstore.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> internalServerException(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(),exception);
        return new ResponseEntity(ResponseDto.buildError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ResponseDto> recordNotFoundException(RecordNotFoundException exception, WebRequest request) {
        log.error(exception.getMessage(),exception);
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(ErrorResponse.builder().fieldName(exception.getFieldName()).message(exception.getMessage()).build());
        return ResponseEntity.badRequest().body(ResponseDto.buildError(errorResponses));
    }

    @ExceptionHandler({CustomerAuthenticationException.class,InternalAuthenticationServiceException.class})
    public ResponseEntity<ResponseDto> customerAuthenticationException(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(),exception);
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(ErrorResponse.builder().fieldName("email").message(exception.getMessage()).build());
        errorResponses.add(ErrorResponse.builder().fieldName("password").message(exception.getMessage()).build());
        return new ResponseEntity(ResponseDto.buildError(errorResponses), HttpStatus.UNAUTHORIZED);
    }

}
