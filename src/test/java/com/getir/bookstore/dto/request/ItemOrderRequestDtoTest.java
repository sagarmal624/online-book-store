package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ItemOrderRequestDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new ItemOrderRequestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(0);
        assertTrue(itemOrderRequestDto.canEqual(itemOrderRequestDto1));
    }

    @Test
    void testConstructor() {
        ItemOrderRequestDto actualItemOrderRequestDto = new ItemOrderRequestDto();
        actualItemOrderRequestDto.setBookId(123L);
        actualItemOrderRequestDto.setQuantity(1);
        assertEquals(123L, actualItemOrderRequestDto.getBookId().longValue());
        assertEquals(1, actualItemOrderRequestDto.getQuantity().intValue());
        assertEquals("ItemOrderRequestDto(bookId=123, quantity=1)", actualItemOrderRequestDto.toString());
    }

    @Test
    void testEquals() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(1);
        assertFalse(itemOrderRequestDto.equals(null));
    }

    @Test
    void testEquals2() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(1);
        assertFalse(itemOrderRequestDto.equals("Different type to ItemOrderRequestDto"));
    }

    @Test
    void testEquals3() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(1);
        assertTrue(itemOrderRequestDto.equals(itemOrderRequestDto));
        int expectedHashCodeResult = itemOrderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, itemOrderRequestDto.hashCode());
    }

    @Test
    void testEquals4() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(1);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(1);
        assertTrue(itemOrderRequestDto.equals(itemOrderRequestDto1));
        int expectedHashCodeResult = itemOrderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, itemOrderRequestDto1.hashCode());
    }

    @Test
    void testEquals5() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(0L);
        itemOrderRequestDto.setQuantity(1);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(1);
        assertFalse(itemOrderRequestDto.equals(itemOrderRequestDto1));
    }

    @Test
    void testEquals6() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(null);
        itemOrderRequestDto.setQuantity(1);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(1);
        assertFalse(itemOrderRequestDto.equals(itemOrderRequestDto1));
    }

    @Test
    void testEquals7() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(1);
        assertFalse(itemOrderRequestDto.equals(itemOrderRequestDto1));
    }

    @Test
    void testEquals8() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(null);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(1);
        assertFalse(itemOrderRequestDto.equals(itemOrderRequestDto1));
    }

    @Test
    void testEquals9() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(null);
        itemOrderRequestDto.setQuantity(1);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(null);
        itemOrderRequestDto1.setQuantity(1);
        assertTrue(itemOrderRequestDto.equals(itemOrderRequestDto1));
        int expectedHashCodeResult = itemOrderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, itemOrderRequestDto1.hashCode());
    }

    @Test
    void testEquals10() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(null);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(null);
        assertTrue(itemOrderRequestDto.equals(itemOrderRequestDto1));
        int expectedHashCodeResult = itemOrderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, itemOrderRequestDto1.hashCode());
    }
}

