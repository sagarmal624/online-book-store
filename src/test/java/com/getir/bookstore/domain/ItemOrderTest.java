package com.getir.bookstore.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(SpringExtension.class)
class ItemOrderTest {
    @Test
    void testConstructor() {
        ItemOrder actualItemOrder = new ItemOrder();
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");
        actualItemOrder.setBook(book);
        actualItemOrder.setQuantity(1);
        assertSame(book, actualItemOrder.getBook());
        assertEquals(1, actualItemOrder.getQuantity().intValue());
    }
}

