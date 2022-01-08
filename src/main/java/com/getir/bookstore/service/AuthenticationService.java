package com.getir.bookstore.service;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.AuthenticationResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    AuthenticationResponseDto getToken(AuthenticationRequestDto authenticationRequestDto);

    Authentication authenticate(AuthenticationRequestDto authenticationRequestDto);

    Customer getLoginCustomer();
}
