package com.getir.bookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.CustomerRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegisterCustomer() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setUserName("janedoe");
        when(this.customerRepository.save((Customer) any())).thenReturn(customer);

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUserName("janedoe");
        ResponseDto<Boolean> actualRegisterCustomerResult = this.customerServiceImpl.registerCustomer(customerRegisterDto);
        assertEquals(2000, actualRegisterCustomerResult.getCode().intValue());
        assertTrue(actualRegisterCustomerResult.getSuccess());
        assertEquals("Record is Saved successfully", actualRegisterCustomerResult.getMessage());
        assertNull(actualRegisterCustomerResult.getErrors());
        assertNull(actualRegisterCustomerResult.getData());
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.customerRepository).save((Customer) any());
    }

    @Test
    void testRegisterCustomer2() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.customerRepository.save((Customer) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setAddress("42 Main St");
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("iloveyou");
        customerRegisterDto.setUserName("janedoe");
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.registerCustomer(customerRegisterDto));
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.customerRepository).save((Customer) any());
    }

    @Test
    void testGetCustomers() {
        when(this.customerRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.getCustomers(pageRequestDto));
        verify(this.customerRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(this.customerRepository.findByUserName((String) any())).thenReturn(ofResult);
        assertSame(customer, this.customerServiceImpl.loadUserByUsername("janedoe"));
        verify(this.customerRepository).findByUserName((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.customerRepository.findByUserName((String) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.loadUserByUsername("janedoe"));
        verify(this.customerRepository).findByUserName((String) any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(this.customerRepository.findByUserName((String) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.loadUserByUsername("janedoe"));
        verify(this.customerRepository).findByUserName((String) any());
    }
}

