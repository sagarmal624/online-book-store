package com.getir.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

class AuthenticationControllerTest {
    @Test
    void testSignin() {
        AuthenticationController authenticationController = new AuthenticationController();

        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setPassword("iloveyou");
        authenticationRequestDto.setUserName("janedoe");
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualSigninResult = authenticationController.signin(authenticationRequestDto,
                bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualSigninResult.toString());
        assertTrue(actualSigninResult.getHeaders().isEmpty());
        assertTrue(actualSigninResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualSigninResult.getStatusCode());
        ResponseDto body = actualSigninResult.getBody();
        assertNull(body.getData());
        assertEquals(1003, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertEquals(objectErrorList, body.getErrors());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        verify(beanPropertyBindingResult).getAllErrors();
        verify(beanPropertyBindingResult).hasErrors();
        assertNull(bindException.getBindingResult().getFieldError());
    }
}

