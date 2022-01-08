package com.getir.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.dto.request.ItemOrderRequestDto;
import com.getir.bookstore.dto.request.OrderFilterRequestDto;
import com.getir.bookstore.dto.request.OrderRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;

import java.time.LocalDateTime;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

class OrderControllerTest {
    @Test
    void testCreateOrder() {
        OrderController orderController = new OrderController();

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        orderRequestDto.setOrders(itemOrderRequestDtoList);
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualCreateOrderResult = orderController.createOrder(orderRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualCreateOrderResult.toString());
        assertTrue(actualCreateOrderResult.getHeaders().isEmpty());
        assertTrue(actualCreateOrderResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualCreateOrderResult.getStatusCode());
        ResponseDto body = actualCreateOrderResult.getBody();
        assertNull(body.getData());
        assertEquals(1003, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertEquals(itemOrderRequestDtoList, body.getErrors());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        verify(beanPropertyBindingResult).getAllErrors();
        verify(beanPropertyBindingResult).hasErrors();
        assertNull(bindException.getSuppressedFields());
    }

    @Test
    void testGetAllOrder() {
        OrderController orderController = new OrderController();

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualAllOrder = orderController.getAllOrder(pageRequestDto, bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualAllOrder.toString());
        assertTrue(actualAllOrder.getHeaders().isEmpty());
        assertTrue(actualAllOrder.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualAllOrder.getStatusCode());
        ResponseDto body = actualAllOrder.getBody();
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
    void testFilterByDateRange() {
        OrderController orderController = new OrderController();

        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseEntity<ResponseDto> actualFilterByDateRangeResult = orderController.filterByDateRange(orderFilterRequestDto,
                bindException);
        assertEquals(
                "<400 BAD_REQUEST Bad Request,ResponseDto(code=1003, message=Data Validation Errors Occurred, success=false,"
                        + " data=null, errors=[]),[]>",
                actualFilterByDateRangeResult.toString());
        assertTrue(actualFilterByDateRangeResult.getHeaders().isEmpty());
        assertTrue(actualFilterByDateRangeResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualFilterByDateRangeResult.getStatusCode());
        ResponseDto body = actualFilterByDateRangeResult.getBody();
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

