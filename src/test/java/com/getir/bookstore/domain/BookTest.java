package com.getir.bookstore.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BookTest {
    @Test
    void testCanEqual() {
        assertFalse((new Book()).canEqual("Other"));
    }

    @Test
    void testConstructor() {
        Book actualBook = new Book();
        actualBook.setAuthor("JaneDoe");
        actualBook.setDescription("The characteristics of someone or something");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualBook.setPrice(valueOfResult);
        actualBook.setTitle("Dr");
        assertEquals("JaneDoe", actualBook.getAuthor());
        assertEquals("The characteristics of someone or something", actualBook.getDescription());
        assertSame(valueOfResult, actualBook.getPrice());
        assertEquals("Dr", actualBook.getTitle());
        assertEquals("Book(title=Dr, author=JaneDoe, description=The characteristics of someone or something, price=42)",
                actualBook.toString());
    }

    @Test
    void testEquals() {
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
        assertFalse(book.equals(null));
    }

    @Test
    void testEquals2() {
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
        assertFalse(book.equals("Different type to Book"));
    }

    @Test
    void testEquals3() {
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
        assertTrue(book.equals(book));
        int expectedHashCodeResult = book.hashCode();
        assertEquals(expectedHashCodeResult, book.hashCode());
    }

    @Test
    void testEquals4() {
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
        assertTrue(book.equals(book1));
        int expectedHashCodeResult = book.hashCode();
        assertEquals(expectedHashCodeResult, book1.hashCode());
    }

    @Test
    void testEquals5() {
        Book book = new Book();
        book.setAuthor("Dr");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals6() {
        Book book = new Book();
        book.setAuthor(null);
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals7() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("Dr");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals8() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription(null);
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals9() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(0L));
        book.setTitle("Dr");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals10() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(null);
        book.setTitle("Dr");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals11() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("JaneDoe");

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
        assertFalse(book.equals(book1));
    }

    @Test
    void testEquals12() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle(null);

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
        assertFalse(book.equals(book1));
    }
}

