package com.getir.bookstore.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BaseEntityTest {
    @Test
    void testCanEqual() {
        assertFalse((new Book()).canEqual("Other"));
    }

    @Test
    void testEquals() {
        assertFalse((new Book()).equals(null));
        assertFalse((new Book()).equals("Different type to BaseEntity"));
        assertFalse((new Book()).equals(0));
    }

    @Test
    void testEquals2() {
        Book book = new Book();
        assertTrue(book.equals(book));
        int expectedHashCodeResult = book.hashCode();
        assertEquals(expectedHashCodeResult, book.hashCode());
    }

    @Test
    void testEquals3() {
        Book book = new Book();
        Book book1 = new Book();
        assertTrue(book.equals(book1));
        int expectedHashCodeResult = book.hashCode();
        assertEquals(expectedHashCodeResult, book1.hashCode());
    }

    @Test
    void testGetCreatedBy() {
        assertNull((new Book()).getCreatedBy());
    }

    @Test
    void testGetCreatedDate() {
        assertNull((new Book()).getCreatedDate());
    }

    @Test
    void testGetId() {
        assertNull((new Book()).getId());
    }

    @Test
    void testGetLastModifiedBy() {
        assertNull((new Book()).getLastModifiedBy());
    }

    @Test
    void testGetLastModifiedDate() {
        assertNull((new Book()).getLastModifiedDate());
    }

    @Test
    void testSetCreatedBy() {
        Book book = new Book();
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        assertEquals("Jan 1, 2020 8:00am GMT+0100", book.getCreatedBy());
    }

    @Test
    void testSetCreatedDate() {
        Book book = new Book();
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        book.setCreatedDate(ofResult);
        assertSame(ofResult, book.getCreatedDate());
    }

    @Test
    void testSetId() {
        Book book = new Book();
        book.setId(123L);
        assertEquals(123L, book.getId().longValue());
    }

    @Test
    void testSetLastModifiedBy() {
        Book book = new Book();
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        assertEquals("Jan 1, 2020 9:00am GMT+0100", book.getLastModifiedBy());
    }

    @Test
    void testSetLastModifiedDate() {
        Book book = new Book();
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        book.setLastModifiedDate(ofResult);
        assertSame(ofResult, book.getLastModifiedDate());
    }

    @Test
    void testToString() {
        assertEquals("Book(title=null, author=null, description=null, price=null)", (new Book()).toString());
    }
}

