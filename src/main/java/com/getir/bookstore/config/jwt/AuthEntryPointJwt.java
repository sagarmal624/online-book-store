package com.getir.bookstore.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.constant.enums.BookStoreErrorCode;
import com.getir.bookstore.dto.response.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    static  private  final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseDto responseDto = ResponseDto.buildError();
        responseDto.setCode(BookStoreErrorCode.UN_AUTHORIZATION.getCode());
        responseDto.setMessage("Unauthorized." + authException.getMessage());
        responseDto.setSuccess(false);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseDto);
    }

}
