package com.getir.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

class CustomerControllerTest {
    @Test
    void testGetCustomers() {
        CustomerController customerController = new CustomerController();

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualCustomers = customerController.getCustomers(pageRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualCustomers.toString());
        assertTrue(actualCustomers.getHeaders().isEmpty());
        assertTrue(actualCustomers.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualCustomers.getStatusCode());
        ResponseDto body = actualCustomers.getBody();
        assertNull(body.getData());
        assertEquals(1003, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertEquals(objectErrorList, body.getErrors());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        verify(beanPropertyBindingResult).getAllErrors();
        verify(beanPropertyBindingResult).hasErrors();
        assertNull(bindException.getSuppressedFields());
    }

    @Test
    void testGetCustomerOrders() {
        CustomerController customerController = new CustomerController();

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualCustomerOrders = customerController.getCustomerOrders(123L, pageRequestDto,
                bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualCustomerOrders.toString());
        assertTrue(actualCustomerOrders.getHeaders().isEmpty());
        assertTrue(actualCustomerOrders.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualCustomerOrders.getStatusCode());
        ResponseDto body = actualCustomerOrders.getBody();
        assertNull(body.getData());
        assertEquals(1003, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertEquals(objectErrorList, body.getErrors());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        verify(beanPropertyBindingResult).getAllErrors();
        verify(beanPropertyBindingResult).hasErrors();
        assertNull(bindException.getSuppressedFields());
    }
}

