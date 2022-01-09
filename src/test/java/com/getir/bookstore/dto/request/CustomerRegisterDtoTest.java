package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomerRegisterDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new CustomerRegisterDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertTrue(customerRegisterDto.canEqual(customerRegisterDto1));
    }

    @Test
    void testConstructor() {
        CustomerRegisterDto actualCustomerRegisterDto = new CustomerRegisterDto();
        actualCustomerRegisterDto.setAddress("42 Main St");
        actualCustomerRegisterDto.setFirstName("Jane");
        actualCustomerRegisterDto.setLastName("Doe");
        actualCustomerRegisterDto.setMobileNumber("42");
        actualCustomerRegisterDto.setPassword("iloveyou");
        actualCustomerRegisterDto.setUsername("janedoe");
        assertEquals("42 Main St", actualCustomerRegisterDto.getAddress());
        assertEquals("Jane", actualCustomerRegisterDto.getFirstName());
        assertEquals("Doe", actualCustomerRegisterDto.getLastName());
        assertEquals("42", actualCustomerRegisterDto.getMobileNumber());
        assertEquals("iloveyou", actualCustomerRegisterDto.getPassword());
        assertEquals("janedoe", actualCustomerRegisterDto.getUsername());
        assertEquals(
                "CustomerRegisterDto(firstName=Jane, lastName=Doe, username=janedoe, password=iloveyou, mobileNumber=42,"
                        + " address=42 Main St)",
                actualCustomerRegisterDto.toString());
    }

    @Test
    void testEquals() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(null));
    }

    @Test
    void testEquals2() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals("Different type to CustomerRegisterDto"));
    }

    @Test
    void testEquals3() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");
        assertTrue(customerRegisterDto.equals(customerRegisterDto));
        int expectedHashCodeResult = customerRegisterDto.hashCode();
        assertEquals(expectedHashCodeResult, customerRegisterDto.hashCode());
    }

    @Test
    void testEquals4() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertTrue(customerRegisterDto.equals(customerRegisterDto1));
        int expectedHashCodeResult = customerRegisterDto.hashCode();
        assertEquals(expectedHashCodeResult, customerRegisterDto1.hashCode());
    }

    @Test
    void testEquals5() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("Jane");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals6() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress(null);
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals7() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Doe");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals8() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName(null);
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals9() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Jane");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals10() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName(null);
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals11() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("Jane");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals12() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber(null);
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals13() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("Jane");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals14() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword(null);
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals15() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("Jane");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals16() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername(null);

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertFalse(customerRegisterDto.equals(customerRegisterDto1));
    }

    @Test
    void testEquals17() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress(null);
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress(null);
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertTrue(customerRegisterDto.equals(customerRegisterDto1));
        int expectedHashCodeResult = customerRegisterDto.hashCode();
        assertEquals(expectedHashCodeResult, customerRegisterDto1.hashCode());
    }

    @Test
    void testEquals18() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName(null);
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName(null);
        customerRegisterDto1.setLastName("Doe");
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertTrue(customerRegisterDto.equals(customerRegisterDto1));
        int expectedHashCodeResult = customerRegisterDto.hashCode();
        assertEquals(expectedHashCodeResult, customerRegisterDto1.hashCode());
    }

    @Test
    void testEquals19() {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName(null);
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUsername("janedoe");

        CustomerRegisterDto customerRegisterDto1 = new CustomerRegisterDto();
        customerRegisterDto1.setAddress("42 Main St");
        customerRegisterDto1.setFirstName("Jane");
        customerRegisterDto1.setLastName(null);
        customerRegisterDto1.setMobileNumber("42");
        customerRegisterDto1.setPassword("iloveyou");
        customerRegisterDto1.setUsername("janedoe");
        assertTrue(customerRegisterDto.equals(customerRegisterDto1));
        int expectedHashCodeResult = customerRegisterDto.hashCode();
        assertEquals(expectedHashCodeResult, customerRegisterDto1.hashCode());
    }
}

