package com.getir.bookstore.dto.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(SpringExtension.class)
class ItemOrderDtoTest {
    @Test
    void testConstructor() {
        ItemOrderDto actualItemOrderDto = new ItemOrderDto();
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("JaneDoe");
        bookDto.setDescription("The characteristics of someone or something");
        bookDto.setId(123L);
        bookDto.setPrice(BigDecimal.valueOf(42L));
        bookDto.setTitle("Dr");
        actualItemOrderDto.setBook(bookDto);
        actualItemOrderDto.setQuantity(1);
        assertSame(bookDto, actualItemOrderDto.getBook());
        assertEquals(1, actualItemOrderDto.getQuantity().intValue());
        assertEquals("ItemOrderDto(quantity=1, book=BookDto(title=Dr, author=JaneDoe, description=The characteristics of"
                + " someone or something, price=42))", actualItemOrderDto.toString());
    }
}

