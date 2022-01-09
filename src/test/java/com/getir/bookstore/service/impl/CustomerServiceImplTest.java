package com.getir.bookstore.service.impl;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.CustomerDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void testRegisterCustomerCase1() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("test1234");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.customerRepository.save((Customer) any())).thenReturn(customer);

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("india");
        customerRegisterDto.setEmail("janedoe");
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
    void testRegisterCustomerCase2() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        when(this.customerRepository.save((Customer) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();

        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("india");
        customerRegisterDto.setEmail("janedoe");
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.registerCustomer(customerRegisterDto));
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.customerRepository).save((Customer) any());
    }
    @Test
    void testRegisterCustomerCase3() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("test1234");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.customerRepository.save((Customer) any())).thenReturn(customer);

        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();

        customerRegisterDto.setFirstName("Jane");
        customerRegisterDto.setLastName("Doe");
        customerRegisterDto.setMobileNumber("42");
        customerRegisterDto.setPassword("india");
        customerRegisterDto.setEmail("janedoe");
        ResponseDto<Boolean> actualRegisterCustomerResult = this.customerServiceImpl.registerCustomer(customerRegisterDto);
        assertEquals(1000, actualRegisterCustomerResult.getCode().intValue());
        assertFalse(actualRegisterCustomerResult.getSuccess());
    }
    @Test
    void testGetCustomersFailureCase() {
        when(this.customerRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.getCustomers(pageRequestDto));
        verify(this.customerRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
    @Test
    void testGetCustomersWithSuccess() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("india");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        Page<Customer> page = Mockito.mock(Page.class);
        List<Customer> customerList=new ArrayList<>();
        customerList.add(customer);
        when(page.getPageable()).thenReturn(PageRequest.of(0, 10));
        when(page.getContent()).thenReturn(customerList);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(customerRepository.findAll(Mockito.isA(Pageable.class))).thenReturn(page);
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(10);
        ResponseDto<PageResponseDto<CustomerDto>> responseDtoResponseDto = customerServiceImpl.getCustomers(pageRequestDto);
        assertNotNull(responseDtoResponseDto);
        assertNotNull(responseDtoResponseDto.getData());
        assertNotNull(responseDtoResponseDto.getData().getRecords());
        assertEquals(1,responseDtoResponseDto.getData().getRecords().size());
        assertEquals(customer.getEmail(),responseDtoResponseDto.getData().getRecords().get(0).getEmail());
    }
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("india");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(this.customerRepository.findByEmail((String) any())).thenReturn(ofResult);
        assertSame(customer, this.customerServiceImpl.loadUserByUsername("janedoe"));
        verify(this.customerRepository).findByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.customerRepository.findByEmail((String) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.loadUserByUsername("janedoe"));
        verify(this.customerRepository).findByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(this.customerRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> this.customerServiceImpl.loadUserByUsername("janedoe"));
        verify(this.customerRepository).findByEmail((String) any());
    }
}

