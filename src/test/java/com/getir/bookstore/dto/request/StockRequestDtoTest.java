package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class StockRequestDtoTest {
    @Test
    void testBuilder() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by builder()
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        StockRequestDto.builder();
    }

    @Test
    void testCanEqual() {
        assertFalse((new StockRequestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        StockRequestDto stockRequestDto = new StockRequestDto();
        assertTrue(stockRequestDto.canEqual(new StockRequestDto()));
    }

    @Test
    void testConstructor() {
        StockRequestDto actualStockRequestDto = new StockRequestDto();
        actualStockRequestDto.setBookId(123L);
        actualStockRequestDto.setQuantity(1);
        assertEquals(123L, actualStockRequestDto.getBookId().longValue());
        assertEquals(1, actualStockRequestDto.getQuantity().intValue());
        assertEquals("StockRequestDto(bookId=123, quantity=1)", actualStockRequestDto.toString());
    }

    @Test
    void testConstructor2() {
        StockRequestDto actualStockRequestDto = new StockRequestDto(123L, 1);
        actualStockRequestDto.setBookId(123L);
        actualStockRequestDto.setQuantity(1);
        assertEquals(123L, actualStockRequestDto.getBookId().longValue());
        assertEquals(1, actualStockRequestDto.getQuantity().intValue());
        assertEquals("StockRequestDto(bookId=123, quantity=1)", actualStockRequestDto.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new StockRequestDto()).equals(null));
        assertFalse((new StockRequestDto()).equals("Different type to StockRequestDto"));
    }

    @Test
    void testEquals2() {
        StockRequestDto stockRequestDto = new StockRequestDto();
        assertTrue(stockRequestDto.equals(stockRequestDto));
        int expectedHashCodeResult = stockRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, stockRequestDto.hashCode());
    }

    @Test
    void testEquals3() {
        StockRequestDto stockRequestDto = new StockRequestDto();
        StockRequestDto stockRequestDto1 = new StockRequestDto();
        assertTrue(stockRequestDto.equals(stockRequestDto1));
        int expectedHashCodeResult = stockRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, stockRequestDto1.hashCode());
    }

    @Test
    void testEquals4() {
        StockRequestDto stockRequestDto = new StockRequestDto(123L, 0);
        assertFalse(stockRequestDto.equals(new StockRequestDto()));
    }

    @Test
    void testEquals5() {
        StockRequestDto stockRequestDto = new StockRequestDto();
        assertFalse(stockRequestDto.equals(new StockRequestDto(123L, 0)));
    }

    @Test
    void testEquals6() {
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setQuantity(0);
        assertFalse(stockRequestDto.equals(new StockRequestDto()));
    }

    @Test
    void testEquals7() {
        StockRequestDto stockRequestDto = new StockRequestDto(123L, 0);
        StockRequestDto stockRequestDto1 = new StockRequestDto(123L, 0);

        assertTrue(stockRequestDto.equals(stockRequestDto1));
        int expectedHashCodeResult = stockRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, stockRequestDto1.hashCode());
    }

    @Test
    void testEquals8() {
        StockRequestDto stockRequestDto = new StockRequestDto();

        StockRequestDto stockRequestDto1 = new StockRequestDto();
        stockRequestDto1.setQuantity(0);
        assertFalse(stockRequestDto.equals(stockRequestDto1));
    }
}

