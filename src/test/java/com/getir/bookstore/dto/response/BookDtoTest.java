package com.getir.bookstore.dto.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(SpringExtension.class)
class BookDtoTest {
    @Test
    void testConstructor() {
        BookDto actualBookDto = new BookDto();
        actualBookDto.setAuthor("JaneDoe");
        actualBookDto.setDescription("The characteristics of someone or something");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualBookDto.setPrice(valueOfResult);
        actualBookDto.setTitle("Dr");
        assertEquals("JaneDoe", actualBookDto.getAuthor());
        assertEquals("The characteristics of someone or something", actualBookDto.getDescription());
        assertSame(valueOfResult, actualBookDto.getPrice());
        assertEquals("Dr", actualBookDto.getTitle());
        assertEquals("BookDto(title=Dr, author=JaneDoe, description=The characteristics of someone or something, price=42)",
                actualBookDto.toString());
    }
}

