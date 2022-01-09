package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BookRequestDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new BookRequestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        BookRequestDto bookRequestDto = new BookRequestDto();

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertTrue(bookRequestDto.canEqual(bookRequestDto1));
    }

    @Test
    void testConstructor() {
        BookRequestDto actualBookRequestDto = new BookRequestDto();
        actualBookRequestDto.setAuthor("JaneDoe");
        actualBookRequestDto.setDescription("The characteristics of someone or something");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualBookRequestDto.setPrice(valueOfResult);
        actualBookRequestDto.setTitle("Dr");
        assertEquals("JaneDoe", actualBookRequestDto.getAuthor());
        assertEquals("The characteristics of someone or something", actualBookRequestDto.getDescription());
        assertSame(valueOfResult, actualBookRequestDto.getPrice());
        assertEquals("Dr", actualBookRequestDto.getTitle());
        assertEquals("BookRequestDto(title=Dr, author=JaneDoe, description=The characteristics of someone or something,"
                + " price=42)", actualBookRequestDto.toString());
    }

    @Test
    void testEquals() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");
        assertFalse(bookRequestDto.equals(null));
    }

    @Test
    void testEquals2() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");
        assertFalse(bookRequestDto.equals("Different type to BookRequestDto"));
    }

    @Test
    void testEquals3() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");
        assertTrue(bookRequestDto.equals(bookRequestDto));
        int expectedHashCodeResult = bookRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, bookRequestDto.hashCode());
    }

    @Test
    void testEquals4() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertTrue(bookRequestDto.equals(bookRequestDto1));
        int expectedHashCodeResult = bookRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, bookRequestDto1.hashCode());
    }

    @Test
    void testEquals5() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("Dr");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals6() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor(null);
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals7() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("Dr");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals8() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription(null);
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals9() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(0L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals10() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(null);
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals11() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("JaneDoe");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals12() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle(null);

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertFalse(bookRequestDto.equals(bookRequestDto1));
    }

    @Test
    void testEquals13() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor(null);
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor(null);
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertTrue(bookRequestDto.equals(bookRequestDto1));
        int expectedHashCodeResult = bookRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, bookRequestDto1.hashCode());
    }

    @Test
    void testEquals14() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription(null);
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription(null);
        bookRequestDto1.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto1.setTitle("Dr");
        assertTrue(bookRequestDto.equals(bookRequestDto1));
        int expectedHashCodeResult = bookRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, bookRequestDto1.hashCode());
    }

    @Test
    void testEquals15() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(null);
        bookRequestDto.setTitle("Dr");

        BookRequestDto bookRequestDto1 = new BookRequestDto();
        bookRequestDto1.setAuthor("JaneDoe");
        bookRequestDto1.setDescription("The characteristics of someone or something");
        bookRequestDto1.setPrice(null);
        bookRequestDto1.setTitle("Dr");
        assertTrue(bookRequestDto.equals(bookRequestDto1));
        int expectedHashCodeResult = bookRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, bookRequestDto1.hashCode());
    }
}

