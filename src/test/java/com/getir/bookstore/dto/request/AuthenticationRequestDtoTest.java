package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class AuthenticationRequestDtoTest {
    @Test
    void testConstructor() {
        AuthenticationRequestDto actualAuthenticationRequestDto = new AuthenticationRequestDto();
        actualAuthenticationRequestDto.setPassword("iloveyou");
        actualAuthenticationRequestDto.setUsername("janedoe");
        assertEquals("iloveyou", actualAuthenticationRequestDto.getPassword());
        assertEquals("janedoe", actualAuthenticationRequestDto.getUsername());
    }
}

