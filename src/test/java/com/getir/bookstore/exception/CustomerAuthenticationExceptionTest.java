package com.getir.bookstore.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerAuthenticationExceptionTest {
    @Test
    void testConstructor() {
        CustomerAuthenticationException actualCustomerAuthenticationException = new CustomerAuthenticationException(
                "An error occurred");
        assertNull(actualCustomerAuthenticationException.getCause());
        assertEquals("com.getir.bookstore.exception.CustomerAuthenticationException: An error occurred",
                actualCustomerAuthenticationException.toString());
        assertEquals(0, actualCustomerAuthenticationException.getSuppressed().length);
        assertEquals("An error occurred", actualCustomerAuthenticationException.getMessage());
        assertEquals("An error occurred", actualCustomerAuthenticationException.getLocalizedMessage());
    }
}

