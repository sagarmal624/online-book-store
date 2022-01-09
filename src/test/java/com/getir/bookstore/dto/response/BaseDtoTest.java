package com.getir.bookstore.dto.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class BaseDtoTest {
    @Test
    void testConstructor() {
        BaseDto actualBaseDto = new BaseDto();
        actualBaseDto.setId(123L);
        assertEquals(123L, actualBaseDto.getId().longValue());
    }
}

