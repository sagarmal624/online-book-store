package com.getir.bookstore.service.impl;

import com.getir.bookstore.config.jwt.JwtTokenProvider;
import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.AuthenticationResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import com.getir.bookstore.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    static final int TOKEN_EXPIRATION = 3600000;

    @Override
    public AuthenticationResponseDto getToken(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authentication = authenticate(authenticationRequestDto);
        if (authentication.isAuthenticated()) {
            String token = jwtTokenProvider.generateJwtToken(authentication);
            return AuthenticationResponseDto.builder().token(token).email(authentication.getName()).expired(TOKEN_EXPIRATION).build();
        }
        throw new CustomerAuthenticationException("Invalid Username or Password");
    }

    @Override
    public Authentication authenticate(AuthenticationRequestDto authenticationRequestDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword()));

    }

    public Customer getLoginCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (Customer)auth.getPrincipal();
    }

}
