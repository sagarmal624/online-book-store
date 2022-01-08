package com.getir.bookstore.service.impl;

import com.getir.bookstore.config.jwt.JwtTokenProvider;
import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.AuthenticationResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    CustomerRepository customerRepository;

    @Value("${book.store.jwt.token.expire.time}")
    private int jwtExpirationMs;

    @Override
    public AuthenticationResponseDto getToken(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authentication = authenticate(authenticationRequestDto);
        if (authentication.isAuthenticated()) {
            String token = jwtTokenProvider.generateJwtToken(authentication);
            return AuthenticationResponseDto.builder().token(token).userName(authentication.getName()).expired(jwtExpirationMs).build();
        }
        throw new CustomerAuthenticationException("Invalid Username or Password");
    }

    @Override
    public Authentication authenticate(AuthenticationRequestDto authenticationRequestDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUserName(), authenticationRequestDto.getPassword()));

    }

    public Customer getLoginCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) auth.getPrincipal();
        return customer;
    }

}
