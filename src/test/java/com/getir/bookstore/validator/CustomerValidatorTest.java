package com.getir.bookstore.validator;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.util.BeanUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = {CustomerValidator.class})
@ExtendWith(SpringExtension.class)
class CustomerValidatorTest {
    @MockBean
    CustomerRepository customerRepository;
    @BeforeAll
    static void init() {
        Mockito.mockStatic(BeanUtil.class);
    }
    @Test
    void testSupports() {
        CustomerValidator customerValidator = new CustomerValidator();
        assertFalse(customerValidator.supports(Object.class));
    }

    @Test
    void testValidate() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setUsername("Sagar");
        CustomerValidator customerValidator = new CustomerValidator();
        Mockito.when(BeanUtil.getBean(Mockito.any())).thenReturn(customerRepository);
        Mockito.when(customerRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(customer));
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("India");
        customerRegisterDto.setUsername("Sagar");

        customerValidator.validate(customerRegisterDto,
                new BindException(customerRegisterDto, "com.getir.bookstore.dto.request.CustomerRegisterDto"));
        assertEquals("42 Main St", customerRegisterDto.getAddress());
        assertEquals("Sagar", customerRegisterDto.getUsername());
        assertEquals("India", customerRegisterDto.getPassword());
        assertEquals("42", customerRegisterDto.getMobileNumber());
        assertEquals("Doe", customerRegisterDto.getLastName());
        assertEquals("Jane", customerRegisterDto.getFirstName());
    }

    @Test
    void testValidateWithSuccessCase() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setUsername("");
        CustomerValidator customerValidator = new CustomerValidator();
        Mockito.when(BeanUtil.getBean(Mockito.any())).thenReturn(customerRepository);
        Mockito.when(customerRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(customer));
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("India");
        customerRegisterDto.setUsername("");

        customerValidator.validate(customerRegisterDto,
                new BindException(customerRegisterDto, "com.getir.bookstore.dto.request.CustomerRegisterDto"));
        assertEquals("42 Main St", customerRegisterDto.getAddress());
        assertEquals("", customerRegisterDto.getUsername());
        assertEquals("India", customerRegisterDto.getPassword());
        assertEquals("42", customerRegisterDto.getMobileNumber());
        assertEquals("Doe", customerRegisterDto.getLastName());
        assertEquals("Jane", customerRegisterDto.getFirstName());
    }

    @Test
    void testValidateWithEmptyCustomerRecord() {
        CustomerValidator customerValidator = new CustomerValidator();
        Mockito.when(BeanUtil.getBean(Mockito.any())).thenReturn(customerRepository);
        Mockito.when(customerRepository.findByUsername(Mockito.any())).thenReturn(Optional.empty());
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("India");
        customerRegisterDto.setUsername("");

        customerValidator.validate(customerRegisterDto,
                new BindException(customerRegisterDto, "com.getir.bookstore.dto.request.CustomerRegisterDto"));
        assertEquals("42 Main St", customerRegisterDto.getAddress());
        assertEquals("", customerRegisterDto.getUsername());
        assertEquals("India", customerRegisterDto.getPassword());
        assertEquals("42", customerRegisterDto.getMobileNumber());
        assertEquals("Doe", customerRegisterDto.getLastName());
        assertEquals("Jane", customerRegisterDto.getFirstName());
    }
}

