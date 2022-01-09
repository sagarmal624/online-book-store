package com.getir.bookstore.config.jwt;

import com.getir.bookstore.exception.CustomerAuthenticationException;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.ServletException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthEntryPointJwtTest {
    @Test
    void testCommence() throws IOException, ServletException {
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

