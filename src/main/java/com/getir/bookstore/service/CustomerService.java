package com.getir.bookstore.service;

import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.CustomerDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;

public interface CustomerService {
    ResponseDto<Boolean> registerCustomer(CustomerRegisterDto request);
    ResponseDto<PageResponseDto<CustomerDto>> getCustomers(PageRequestDto pageRequestDto);
}
