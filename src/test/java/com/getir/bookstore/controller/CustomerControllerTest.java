package com.getir.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.constant.enums.OrderStatus;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.dto.response.*;
import com.getir.bookstore.service.CustomerService;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.validator.CustomerValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;
    @MockBean
    CustomerValidator customerValidator;
    @MockBean
    OrderService orderService;
    @Autowired
    private CustomerController customerController;

    @Test
    void testRegisterCustomerSuccess() throws Exception {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setEmail("test@gmail.com");
        customerRegisterDto.setPassword("test@123");
        customerRegisterDto.setFirstName("sagar");
        customerRegisterDto.setLastName("shankhala");
        customerRegisterDto.setMobileNumber("8826100625");
        ResponseDto<Boolean> responseDto = ResponseDto.buildSuccess();
        when(customerService.registerCustomer(any())).thenReturn(responseDto);
        String content = (new ObjectMapper()).writeValueAsString(customerRegisterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testRegisterCustomerFailure() throws Exception {
        CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto();
        customerRegisterDto.setEmail(null);
        customerRegisterDto.setPassword("test@123");
        customerRegisterDto.setFirstName("sagar");
        customerRegisterDto.setLastName("shankhala");
        customerRegisterDto.setMobileNumber("8826100625");
        ResponseDto<Boolean> responseDto = ResponseDto.buildSuccess();
        when(customerService.registerCustomer(any())).thenReturn(responseDto);
        String content = (new ObjectMapper()).writeValueAsString(customerRegisterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetCustomersSuccess() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("sagar");
        customerDto.setLastName("shankhala");
        customerDto.setEmail("sagarmal24@gmal.com");
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerDtoList.add(customerDto);
        ResponseDto<PageResponseDto<CustomerDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<CustomerDto>builder().page(pageDto).records(customerDtoList).build());
        when(customerService.getCustomers(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.CUSTOMER_BASE_URL + "?page=0&size=01")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetCustomersFailure() throws Exception {
     MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
    @Test
    void testGetCustomerOrdersSuccess() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(LocalDateTime.now());
        orderDto.setAmount(BigDecimal.TEN);
        orderDto.setStatus(OrderStatus.IN_PROGRESS);
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<OrderDto>builder().page(pageDto).records(orderDtos).build());
        when(orderService.getOrdersByCustomer(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.CUSTOMER_BASE_URL + "/1/orders?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetCustomerOrderFailure() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.CUSTOMER_BASE_URL +"/1/orders")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetCustomerOrderFailureNotFound() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.CUSTOMER_BASE_URL +"/orders")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
