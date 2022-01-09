package com.getir.bookstore.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class CustomerTest {
    @Test
    void testGetAuthorities() {
        assertTrue((new Customer()).getAuthorities().isEmpty());
    }

    @Test
    void testConstructor() {
        Customer actualCustomer = new Customer();
        assertNull(actualCustomer.getPassword());
        assertNull(actualCustomer.getUsername());
        assertTrue(actualCustomer.isAccountNonExpired());
        assertTrue(actualCustomer.isAccountNonLocked());
        assertTrue(actualCustomer.isCredentialsNonExpired());
        assertTrue(actualCustomer.isEnabled());
    }
}

