package com.getir.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.constant.enums.OrderStatus;
import com.getir.bookstore.dto.request.ItemOrderRequestDto;
import com.getir.bookstore.dto.request.OrderRequestDto;
import com.getir.bookstore.dto.response.OrderDto;
import com.getir.bookstore.dto.response.PageDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.service.OrderService;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private OrderController orderController;

    @Test
    void testCreateOrderSuccess() throws Exception {
        ResponseDto<OrderDto> responseDto = ResponseDto.buildSuccess();
        when(orderService.createOrder(any())).thenReturn(responseDto);
        ItemOrderRequestDto itemOrderDto = new ItemOrderRequestDto();
        itemOrderDto.setBookId(1L);
        itemOrderDto.setQuantity(100);
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(Arrays.asList(itemOrderDto));
        String content = (new ObjectMapper()).writeValueAsString(orderRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.ORDER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
    @Test
    void testCreateOrderFailureWithEmptyCart() throws Exception {
        ResponseDto<OrderDto> responseDto = ResponseDto.buildSuccess();
        when(orderService.createOrder(any())).thenReturn(responseDto);
        ItemOrderRequestDto itemOrderDto = new ItemOrderRequestDto();
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(Arrays.asList(itemOrderDto));
        String content = (new ObjectMapper()).writeValueAsString(orderRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.ORDER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
//    @Test
//    void testAddOrderFailureWithNullQuantity() throws Exception {
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder().quantity(null).build();
//        String content = (new ObjectMapper()).writeValueAsString(orderRequestDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(ApiEndPoint.order_BASE_URL + "/book/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.orderController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//    }
//
//    /* @Test
//     void testAddOrderFailure() throws Exception {
//         ResponseDto<Boolean> responseDto = ResponseDto.buildSuccess();
//         when(orderService.addOrder(any())).thenReturn(responseDto);
//         OrderRequestDto orderRequestDto = new OrderRequestDto();
//         String content = (new ObjectMapper()).writeValueAsString(orderRequestDto);
//         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.BOOK_BASE_URL)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(content);
//         MockMvcBuilders.standaloneSetup(this.orderController)
//                 .build()
//                 .perform(requestBuilder)
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                 .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//     }
// */
//    @Test
//    void testGetOrdersSuccess() throws Exception {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderId(1L);
//        orderDto.setBookId(2L);
//        orderDto.setQuantity(100);
//        PageDto pageDto = PageDto.builder().page(0).size(10).build();
//        List<OrderDto> orderDtoList = new ArrayList<>();
//        orderDtoList.add(orderDto);
//        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<OrderDto>builder().page(pageDto).records(orderDtoList).build());
//        when(orderService.getOrders(any())).thenReturn(responseDtoResponseDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.order_BASE_URL + "?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON);
//        MockMvcBuilders.standaloneSetup(this.orderController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void testGetOrdersFailure() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.order_BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON);
//        MockMvcBuilders.standaloneSetup(this.orderController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//    }
//
//    @Test
//    void testGetOrdersURLNotFound() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.order_BASE_URL + "/test")
//                .contentType(MediaType.APPLICATION_JSON);
//        MockMvcBuilders.standaloneSetup(this.orderController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
    @Test
    void testFindOrderByIdSuccess() throws Exception {

        ResponseDto<OrderDto> responseDtoResponseDto = ResponseDto.buildSuccess();
        when(orderService.getOrdersById(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.ORDER_BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testGetAllOrderSuccess() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(LocalDateTime.now());
        orderDto.setAmount(BigDecimal.TEN);
        orderDto.setStatus(OrderStatus.IN_PROGRESS);
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<OrderDto>builder().page(pageDto).records(orderDtos).build());
        when(orderService.getOrdersByCustomer(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.ORDER_BASE_URL + "?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());  
    }

    @Test
    void testGetAllOrderFailureWithSizeZero() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(LocalDateTime.now());
        orderDto.setAmount(BigDecimal.TEN);
        orderDto.setStatus(OrderStatus.IN_PROGRESS);
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<OrderDto>builder().page(pageDto).records(orderDtos).build());
        when(orderService.getOrdersByCustomer(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.ORDER_BASE_URL + "?page=0")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testFilterByDateRangeSuccess() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(LocalDateTime.now());
        orderDto.setAmount(BigDecimal.TEN);
        orderDto.setStatus(OrderStatus.IN_PROGRESS);
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<OrderDto>builder().page(pageDto).records(orderDtos).build());
        when(orderService.getOrdersByCustomer(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.ORDER_BASE_URL + "/filters?fromDate=2022-01-08T20:16&toDate=2022-06-22T20:16&page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFilterByDateRangeFailureWithEmptyFromDateAndToDate() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(LocalDateTime.now());
        orderDto.setAmount(BigDecimal.TEN);
        orderDto.setStatus(OrderStatus.IN_PROGRESS);
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<OrderDto>builder().page(pageDto).records(orderDtos).build());
        when(orderService.getOrdersByCustomer(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.ORDER_BASE_URL + "/filters?page=0")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
