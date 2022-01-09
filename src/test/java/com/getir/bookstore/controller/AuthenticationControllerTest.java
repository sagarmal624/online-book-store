package com.getir.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.AuthenticationResponseDto;
import com.getir.bookstore.service.AuthenticationService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
public class AuthenticationControllerTest{
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationController authenticationController;

    @Test
    void testSigninSuccess() throws Exception {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("test@gmail.com");
        authenticationRequestDto.setPassword("test@123");
        AuthenticationResponseDto authenticationResponseDto = AuthenticationResponseDto.builder().expired(36000).email("test@gmail.com").token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c").build();
        when(authenticationService.getToken(any())).thenReturn(authenticationResponseDto);
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.OAUTH_BASE_URL + "/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testSigninFailure() throws Exception {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail(null);
        authenticationRequestDto.setPassword(null);
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.OAUTH_BASE_URL + "/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}
