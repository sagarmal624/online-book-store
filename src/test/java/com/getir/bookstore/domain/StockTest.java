package com.getir.bookstore.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(SpringExtension.class)
class StockTest {
    @Test
    void testConstructor() {
        Stock actualStock = new Stock();
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
        actualStock.setBook(book);
        actualStock.setQuantity(1);
        assertSame(book, actualStock.getBook());
        assertEquals(1, actualStock.getQuantity().intValue());
    }

    @Test
    void testConstructor2() {
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
        Stock actualStock = new Stock(1, book);
        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");
        actualStock.setBook(book1);
        actualStock.setQuantity(1);
        Book book2 = actualStock.getBook();
        assertSame(book1, book2);
        assertEquals(book, book2);
        assertEquals(1, actualStock.getQuantity().intValue());
    }
}

