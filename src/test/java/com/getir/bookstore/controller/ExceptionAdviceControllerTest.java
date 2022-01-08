package com.getir.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import com.getir.bookstore.exception.RecordNotFoundException;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

class ExceptionAdviceControllerTest {
    @Test
    void testBookRecordNotFound() {
        ExceptionAdviceController exceptionAdviceController = new ExceptionAdviceController();
        CustomerAuthenticationException exception = new CustomerAuthenticationException("An error occurred");
        ResponseEntity<ResponseDto> actualBookRecordNotFoundResult = exceptionAdviceController.bookRecordNotFound(exception,
                new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualBookRecordNotFoundResult.getHeaders().isEmpty());
        assertTrue(actualBookRecordNotFoundResult.hasBody());
        assertEquals(HttpStatus.UNAUTHORIZED, actualBookRecordNotFoundResult.getStatusCode());
        ResponseDto body = actualBookRecordNotFoundResult.getBody();
        assertNull(body.getData());
        assertEquals(1002, body.getCode().intValue());
        assertFalse(body.getSuccess());
        List<ErrorResponse> errors = body.getErrors();
        assertEquals(2, errors.size());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        ErrorResponse getResult = errors.get(1);
        assertEquals("An error occurred", getResult.getMessage());
        ErrorResponse getResult1 = errors.get(0);
        assertEquals("An error occurred", getResult1.getMessage());
        assertEquals("UserName", getResult1.getFieldName());
        assertEquals("Password", getResult.getFieldName());
    }

    @Test
    void testBookRecordNotFound2() {
        ExceptionAdviceController exceptionAdviceController = new ExceptionAdviceController();
        RecordNotFoundException exception = new RecordNotFoundException("An error occurred", "Field Name");

        ResponseEntity<ResponseDto> actualBookRecordNotFoundResult = exceptionAdviceController.bookRecordNotFound(exception,
                new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualBookRecordNotFoundResult.getHeaders().isEmpty());
        assertTrue(actualBookRecordNotFoundResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualBookRecordNotFoundResult.getStatusCode());
        ResponseDto body = actualBookRecordNotFoundResult.getBody();
        assertNull(body.getData());
        assertEquals(1002, body.getCode().intValue());
        assertFalse(body.getSuccess());
        List<ErrorResponse> errors = body.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Data Validation Errors Occurred", body.getMessage());
        ErrorResponse getResult = errors.get(0);
        assertEquals("Field Name", getResult.getFieldName());
        assertEquals("An error occurred", getResult.getMessage());
    }

    @Test
    void testBookRecordNotFound3() {
        ExceptionAdviceController exceptionAdviceController = new ExceptionAdviceController();
        RuntimeException exception = new RuntimeException("An error occurred");
        ResponseEntity<ResponseDto> actualBookRecordNotFoundResult = exceptionAdviceController.bookRecordNotFound(exception,
                new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals(
                "<500 INTERNAL_SERVER_ERROR Internal Server Error,ResponseDto(code=1000, message=Something Went"
                        + " Wrong.Internal Server Error.Please try again, success=false, data=null, errors=null),[]>",
                actualBookRecordNotFoundResult.toString());
        assertTrue(actualBookRecordNotFoundResult.getHeaders().isEmpty());
        assertTrue(actualBookRecordNotFoundResult.hasBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualBookRecordNotFoundResult.getStatusCode());
        ResponseDto body = actualBookRecordNotFoundResult.getBody();
        assertNull(body.getData());
        assertEquals(1000, body.getCode().intValue());
        assertFalse(body.getSuccess());
        assertNull(body.getErrors());
        assertEquals("Something Went Wrong.Internal Server Error.Please try again", body.getMessage());
    }
}

