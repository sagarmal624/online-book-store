package com.getir.bookstore.dto.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class AuthenticationResponseDtoTest {
    @Test
    void testConstructor() {
        AuthenticationResponseDto actualAuthenticationResponseDto = new AuthenticationResponseDto("janedoe", "ABC123",
                "Type", 1L);
        actualAuthenticationResponseDto.setExpired(1L);
        actualAuthenticationResponseDto.setToken("ABC123");
        actualAuthenticationResponseDto.setType("Type");
        actualAuthenticationResponseDto.setUserName("janedoe");
        assertEquals(1L, actualAuthenticationResponseDto.getExpired());
        assertEquals("ABC123", actualAuthenticationResponseDto.getToken());
        assertEquals("Type", actualAuthenticationResponseDto.getType());
        assertEquals("janedoe", actualAuthenticationResponseDto.getUserName());
        assertEquals("AuthenticationResponseDto(userName=janedoe, token=ABC123, type=Type, expired=1)",
                actualAuthenticationResponseDto.toString());
    }
}

