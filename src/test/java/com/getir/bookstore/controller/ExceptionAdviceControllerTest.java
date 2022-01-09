package com.getir.bookstore.controller;

import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import com.getir.bookstore.exception.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = {ExceptionAdviceController.class})
@ExtendWith(SpringExtension.class)
class ExceptionAdviceControllerTest {
    @Autowired
    ExceptionAdviceController exceptionAdviceController;

    @Test
    void testInternalServerError() {
        ResponseEntity<ResponseDto> responseEntity = exceptionAdviceController.internalServerException(new RuntimeException(), null);
        assertEquals(responseEntity.getStatusCode().value(), 500);
    }

    @Test
    void testRecordNotFoundException() {
        RecordNotFoundException recordNotFoundException=new RecordNotFoundException("Book record is not found","bookId");
        ResponseEntity<ResponseDto> responseEntity = exceptionAdviceController.recordNotFoundException(recordNotFoundException, null);
        assertEquals(responseEntity.getStatusCode().value(), 400);
        assertNotNull(responseEntity.getBody());
    }
    @Test
    void testCustomerAuthenticationException() {
        CustomerAuthenticationException customerAuthenticationException=new CustomerAuthenticationException("Bad Credentails");
        ResponseEntity<ResponseDto> responseEntity = exceptionAdviceController.customerAuthenticationException(customerAuthenticationException, null);
        assertEquals(responseEntity.getStatusCode().value(), 401);
        assertNotNull(responseEntity.getBody());
    }
}
