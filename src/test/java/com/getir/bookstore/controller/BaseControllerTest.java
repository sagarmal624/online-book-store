package com.getir.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

class BaseControllerTest {
    @Test
    void testGetErrors() {
        BaseController baseController = new BaseController();
        ResponseDto<List<ErrorResponse>> actualErrors = baseController.getErrors(new BindException(new BindException(
                new BindException(new BindException(new BeanPropertyBindingResult("Target", "Object Name"))))));
        assertEquals(1003, actualErrors.getCode().intValue());
        assertFalse(actualErrors.getSuccess());
        assertEquals("Data Validation Errors Occurred", actualErrors.getMessage());
        assertTrue(actualErrors.getErrors().isEmpty());
        assertNull(actualErrors.getData());
    }

    @Test
    void testGetErrors2() {
        BaseController baseController = new BaseController();
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseDto<List<ErrorResponse>> actualErrors = baseController.getErrors(bindException);
        assertEquals(1003, actualErrors.getCode().intValue());
        assertFalse(actualErrors.getSuccess());
        assertEquals("Data Validation Errors Occurred", actualErrors.getMessage());
        assertEquals(objectErrorList, actualErrors.getErrors());
        assertNull(actualErrors.getData());
        verify(beanPropertyBindingResult).getAllErrors();
        assertNull(bindException.getBindingResult().getGlobalError());
    }

    @Test
    void testGetErrors3() {
        BaseController baseController = new BaseController();

        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        objectErrorList.add(new ObjectError("Data Validation Errors Occurred", "Data Validation Errors Occurred"));
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(objectErrorList);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(beanPropertyBindingResult))));
        ResponseDto<List<ErrorResponse>> actualErrors = baseController.getErrors(bindException);
        assertEquals(1003, actualErrors.getCode().intValue());
        assertFalse(actualErrors.getSuccess());
        assertEquals("Data Validation Errors Occurred", actualErrors.getMessage());
        assertTrue(actualErrors.getErrors().isEmpty());
        assertNull(actualErrors.getData());
        verify(beanPropertyBindingResult).getAllErrors();
        assertNull(bindException.getBindingResult().getGlobalError());
    }
}

