package com.getir.bookstore.config.jwt;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.exception.CustomerAuthenticationException;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class AuthEntryPointJwtTest {
    @Test
    void testCommence() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        AuthEntryPointJwt authEntryPointJwt = new AuthEntryPointJwt();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        authEntryPointJwt.commence(request, response, new CustomerAuthenticationException("An error occurred"));
    }

    @Test
    void testCommence2() throws IOException, ServletException {
        AuthEntryPointJwt authEntryPointJwt = new AuthEntryPointJwt();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = mock(Response.class);
        when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(new ByteArrayOutputStream(3)));
        doNothing().when(response).setStatus(anyInt());
        doNothing().when(response).setContentType((String) any());
        authEntryPointJwt.commence(request, response, new CustomerAuthenticationException("An error occurred"));
        verify(response).getOutputStream();
        verify(response).setContentType((String) any());
        verify(response).setStatus(anyInt());
    }

    @Test
    void testCommence3() throws IOException, ServletException {
        AuthEntryPointJwt authEntryPointJwt = new AuthEntryPointJwt();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = mock(Response.class);
        when(response.getOutputStream()).thenThrow(new IOException("An error occurred"));
        doNothing().when(response).setStatus(anyInt());
        doNothing().when(response).setContentType((String) any());
        assertThrows(IOException.class,
                () -> authEntryPointJwt.commence(request, response, new CustomerAuthenticationException("An error occurred")));
        verify(response).getOutputStream();
        verify(response).setContentType((String) any());
        verify(response).setStatus(anyInt());
    }
}

