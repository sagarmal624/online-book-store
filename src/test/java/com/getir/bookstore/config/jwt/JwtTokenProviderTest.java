package com.getir.bookstore.config.jwt;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {
    @Test
    void testValidateJwtToken() {
        assertFalse((new JwtTokenProvider()).validateJwtToken("ABC123"));
    }
}

