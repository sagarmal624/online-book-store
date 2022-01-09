package com.getir.bookstore.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
class DataJpaConfigTest {
    @Test
    void testAuditor() {
        assertFalse((new DataJpaConfig()).auditor().getCurrentAuditor().isPresent());
    }
}

