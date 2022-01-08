package com.getir.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

class StockControllerTest {
    @Test
    void testUpdateStockQuantity() {
        StockController stockController = new StockController();
        StockRequestDto stockRequestDto = mock(StockRequestDto.class);
        doNothing().when(stockRequestDto).setBookId((Long) any());
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualUpdateStockQuantityResult = stockController.updateStockQuantity(123L,
                stockRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualUpdateStockQuantityResult.toString());
        assertTrue(actualUpdateStockQuantityResult.getHeaders().isEmpty());
        assertTrue(actualUpdateStockQuantityResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualUpdateStockQuantityResult.getStatusCode());
        ResponseDto body = actualUpdateStockQuantityResult.getBody();
        assertNull(body.getData());
        assertEquals(1003, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertEquals(objectErrorList, body.getErrors());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        verify(beanPropertyBindingResult).getAllErrors();
        verify(beanPropertyBindingResult).hasErrors();
        assertNull(bindException.getBindingResult().getFieldError());
    }

    @Test
    void testUpdateStockQuantity2() {
        StockController stockController = new StockController();
        StockRequestDto stockRequestDto = mock(StockRequestDto.class);
        doNothing().when(stockRequestDto).setBookId((Long) any());
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualUpdateStockQuantityResult = stockController.updateStockQuantity(123L,
                stockRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualUpdateStockQuantityResult.toString());
        assertTrue(actualUpdateStockQuantityResult.getHeaders().isEmpty());
        assertTrue(actualUpdateStockQuantityResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualUpdateStockQuantityResult.getStatusCode());
        ResponseDto body = actualUpdateStockQuantityResult.getBody();
        assertNull(body.getData());
        assertEquals(1003, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertEquals(objectErrorList, body.getErrors());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        verify(beanPropertyBindingResult).getAllErrors();
        verify(beanPropertyBindingResult).hasErrors();
        assertNull(bindException.getBindingResult().getFieldError());
    }

    @Test
    void testGetStocks() {
        StockController stockController = new StockController();

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualStocks = stockController.getStocks(pageRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualStocks.toString());
        assertTrue(actualStocks.getHeaders().isEmpty());
        assertTrue(actualStocks.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualStocks.getStatusCode());
        ResponseDto body = actualStocks.getBody();
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
    void testGetStocks2() {
        StockController stockController = new StockController();

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualStocks = stockController.getStocks(pageRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualStocks.toString());
        assertTrue(actualStocks.getHeaders().isEmpty());
        assertTrue(actualStocks.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualStocks.getStatusCode());
        ResponseDto body = actualStocks.getBody();
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

