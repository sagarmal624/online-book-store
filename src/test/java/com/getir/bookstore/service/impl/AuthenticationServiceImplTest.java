package com.getir.bookstore.service.impl;

import com.getir.bookstore.config.jwt.JwtTokenProvider;
import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.AuthenticationResponseDto;
import com.getir.bookstore.exception.CustomerAuthenticationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthenticationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceImplTest {
    @MockBean

    AuthenticationManager authenticationManager;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    Authentication authentication;
    @MockBean
    SecurityContext securityContext;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @BeforeAll
    static void init() {
        Mockito.mockStatic(SecurityContextHolder.class);
    }

    @Test
    void testGetTokenSuccessCase() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("test");
        authenticationRequestDto.setPassword("India@123");
        when(jwtTokenProvider.generateJwtToken(any())).thenReturn("Bearer token");
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        AuthenticationResponseDto responseDto = authenticationService.getToken(authenticationRequestDto);
        assertNotNull(responseDto);
        assertNotNull(responseDto.getToken());
        assertEquals("Bearer token", responseDto.getToken());
    }

    @Test
    void testGetTokenSuccessFailure() {

        CustomerAuthenticationException exception = assertThrows(
                CustomerAuthenticationException.class,
                () -> {
                    AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
                    authenticationRequestDto.setEmail("test");
                    authenticationRequestDto.setPassword("India@123");
                    when(authentication.isAuthenticated()).thenReturn(false);
                    when(authenticationManager.authenticate(any())).thenReturn(authentication);
                    authenticationService.getToken(authenticationRequestDto);
                }
        );
        assertEquals(exception.getMessage(), "Invalid Username or Password");
    }

    @Test
    void testLoginUser() {
        Customer loginedUser = new Customer();
        loginedUser.setEmail("sagar");
        when(authentication.getPrincipal()).thenReturn(loginedUser);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        Customer customer = authenticationService.getLoginCustomer();
        assertNotNull(customer);
        assertNotNull(customer.getEmail());
        assertEquals(customer.getEmail(), loginedUser.getEmail());
    }
}

