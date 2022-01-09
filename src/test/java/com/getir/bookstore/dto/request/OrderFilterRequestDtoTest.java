package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class OrderFilterRequestDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new OrderFilterRequestDto()).canEqual("Other"));
    }

    @Test
    void testConstructor() {
        OrderFilterRequestDto actualOrderFilterRequestDto = new OrderFilterRequestDto();
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualOrderFilterRequestDto.setFromDate(ofResult);
        LocalDateTime ofResult1 = LocalDateTime.of(1, 1, 1, 1, 1);
        actualOrderFilterRequestDto.setToDate(ofResult1);
        assertSame(ofResult, actualOrderFilterRequestDto.getFromDate());
        assertSame(ofResult1, actualOrderFilterRequestDto.getToDate());
        assertEquals("OrderFilterRequestDto(fromDate=0001-01-01T01:01, toDate=0001-01-01T01:01)",
                actualOrderFilterRequestDto.toString());
    }

    @Test
    void testEquals() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(orderFilterRequestDto.equals(null));
    }

    @Test
    void testEquals2() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(orderFilterRequestDto.equals("Different type to OrderFilterRequestDto"));
    }

    @Test
    void testEquals3() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertTrue(orderFilterRequestDto.equals(orderFilterRequestDto));
        int expectedHashCodeResult = orderFilterRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderFilterRequestDto.hashCode());
    }

    @Test
    void testEquals4() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertTrue(orderFilterRequestDto.equals(orderFilterRequestDto1));
        int expectedHashCodeResult = orderFilterRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderFilterRequestDto1.hashCode());
    }

    @Test
    void testEquals5() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(0, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(orderFilterRequestDto.equals(orderFilterRequestDto1));
    }

    @Test
    void testEquals6() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(null);
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(orderFilterRequestDto.equals(orderFilterRequestDto1));
    }

    @Test
    void testEquals7() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(0, 1, 1, 1, 1));

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(orderFilterRequestDto.equals(orderFilterRequestDto1));
    }

    @Test
    void testEquals8() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(null);

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(orderFilterRequestDto.equals(orderFilterRequestDto1));
    }

    @Test
    void testEquals9() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(null);
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(null);
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertTrue(orderFilterRequestDto.equals(orderFilterRequestDto1));
        int expectedHashCodeResult = orderFilterRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderFilterRequestDto1.hashCode());
    }

    @Test
    void testEquals10() {
        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(null);

        OrderFilterRequestDto orderFilterRequestDto1 = new OrderFilterRequestDto();
        orderFilterRequestDto1.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto1.setPage(1);
        orderFilterRequestDto1.setSize(3);
        orderFilterRequestDto1.setToDate(null);
        assertTrue(orderFilterRequestDto.equals(orderFilterRequestDto1));
        int expectedHashCodeResult = orderFilterRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderFilterRequestDto1.hashCode());
    }
}

