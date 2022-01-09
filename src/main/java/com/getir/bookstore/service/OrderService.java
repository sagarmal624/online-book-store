package com.getir.bookstore.service;

import com.getir.bookstore.dto.request.ItemOrderRequestDto;
import com.getir.bookstore.dto.request.OrderFilterRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.OrderDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;

import java.util.List;


public interface OrderService {

    ResponseDto<OrderDto> createOrder(List<ItemOrderRequestDto> request);

    ResponseDto<PageResponseDto<OrderDto>> getOrdersByCustomer(PageRequestDto pageRequestDto);

    ResponseDto<PageResponseDto<OrderDto>> getOrdersByCustomer(Long customerId, PageRequestDto pageRequestDto);

    ResponseDto<OrderDto> getOrdersById(Long orderId);

    ResponseDto<PageResponseDto<OrderDto>> filterOrdersByDateRange(OrderFilterRequestDto orderFilterRequestDto);

}
