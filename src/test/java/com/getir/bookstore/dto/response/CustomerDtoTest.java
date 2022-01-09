package com.getir.bookstore.dto.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CustomerDtoTest {
    @Test
    void testConstructor() {
        CustomerDto actualCustomerDto = new CustomerDto();
        actualCustomerDto.setAddress("42 Main St");
        actualCustomerDto.setFirstName("Jane");
        actualCustomerDto.setLastName("Doe");
        actualCustomerDto.setMobileNumber("42");
        actualCustomerDto.setUsername("janedoe");
        assertEquals("42 Main St", actualCustomerDto.getAddress());
        assertEquals("Jane", actualCustomerDto.getFirstName());
        assertEquals("Doe", actualCustomerDto.getLastName());
        assertEquals("42", actualCustomerDto.getMobileNumber());
        assertEquals("janedoe", actualCustomerDto.getUsername());
        assertEquals("CustomerDto(firstName=Jane, lastName=Doe, username=janedoe, mobileNumber=42, address=42 Main St)",
                actualCustomerDto.toString());
    }
}

