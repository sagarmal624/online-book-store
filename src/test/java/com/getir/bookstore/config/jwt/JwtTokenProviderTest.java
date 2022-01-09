package com.getir.bookstore.config.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
class JwtTokenProviderTest {
    @Test
    void testValidateJwtToken() {
        assertFalse((new JwtTokenProvider()).validateJwtToken("ABC123"));
    }
}

