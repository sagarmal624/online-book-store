package com.getir.bookstore.util;

import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidatorUtilsTest {
    @Test
    void testGetErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        when(bindingResult.getAllErrors()).thenReturn(objectErrorList);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(bindingResult))));
        ResponseDto<List<ErrorResponse>> actualErrors = ValidatorUtils.getErrors(bindException,
                new AnnotationConfigReactiveWebApplicationContext());
        assertEquals(1008, actualErrors.getCode().intValue());
        assertFalse(actualErrors.getSuccess());
        assertEquals("Data Validation Errors Occurred", actualErrors.getMessage());
        assertEquals(objectErrorList, actualErrors.getErrors());
        assertNull(actualErrors.getData());
        verify(bindingResult).getAllErrors();
        assertNull(bindException.getSuppressedFields());
    }

    @Test
    void testGetErrors2() {
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        objectErrorList.add(new ObjectError("Data Validation Errors Occurred", "Data Validation Errors Occurred"));
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(objectErrorList);
        BindException bindException = new BindException(
                new BindException(new BindException(new BindException(bindingResult))));
        ResponseDto<List<ErrorResponse>> actualErrors = ValidatorUtils.getErrors(bindException,
                new AnnotationConfigReactiveWebApplicationContext());
        assertEquals(1008, actualErrors.getCode().intValue());
        assertFalse(actualErrors.getSuccess());
        assertEquals("Data Validation Errors Occurred", actualErrors.getMessage());
        assertTrue(actualErrors.getErrors().isEmpty());
        assertNull(actualErrors.getData());
        verify(bindingResult).getAllErrors();
        assertNull(bindException.getSuppressedFields());
    }
}

