package com.getir.bookstore.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.getir.bookstore.dto.request.CustomerRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;

class CustomerValidatorTest {
    @Test
    void testSupports() {
        CustomerValidator customerValidator = new CustomerValidator();
        assertFalse(customerValidator.supports(Object.class));
    }

    @Test
    void testSupports2() {
        CustomerValidator customerValidator = new CustomerValidator();
        assertFalse(customerValidator.supports(Object.class));
    }

    @Test
    void testValidate() {
        CustomerValidator customerValidator = new CustomerValidator();

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUserName("");
        customerValidator.validate(customerRegisterDto,
                new BindException(customerRegisterDto, "com.getir.bookstore.dto.request.CustomerRegisterDto"));
        assertEquals("42 Main St", customerRegisterDto.getAddress());
        assertEquals("", customerRegisterDto.getUserName());
        assertEquals("iloveyou", customerRegisterDto.getPassword());
        assertEquals("42", customerRegisterDto.getMobileNumber());
        assertEquals("Doe", customerRegisterDto.getLastName());
        assertEquals("Jane", customerRegisterDto.getFirstName());
    }

    @Test
    void testValidate2() {
        CustomerValidator customerValidator = new CustomerValidator();

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUserName("");
        customerValidator.validate(customerRegisterDto,
                new BindException(customerRegisterDto, "com.getir.bookstore.dto.request.CustomerRegisterDto"));
        assertEquals("42 Main St", customerRegisterDto.getAddress());
        assertEquals("", customerRegisterDto.getUserName());
        assertEquals("iloveyou", customerRegisterDto.getPassword());
        assertEquals("42", customerRegisterDto.getMobileNumber());
        assertEquals("Doe", customerRegisterDto.getLastName());
        assertEquals("Jane", customerRegisterDto.getFirstName());
    }
}

