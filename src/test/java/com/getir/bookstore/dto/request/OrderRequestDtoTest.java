package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class OrderRequestDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new OrderRequestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();

        OrderRequestDto orderRequestDto1 = new OrderRequestDto();
        orderRequestDto1.setOrders(new ArrayList<>());
        assertTrue(orderRequestDto.canEqual(orderRequestDto1));
    }

    @Test
    void testConstructor() {
        OrderRequestDto actualOrderRequestDto = new OrderRequestDto();
        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        actualOrderRequestDto.setOrders(itemOrderRequestDtoList);
        assertSame(itemOrderRequestDtoList, actualOrderRequestDto.getOrders());
        assertEquals("OrderRequestDto(orders=[])", actualOrderRequestDto.toString());
    }

    @Test
    void testEquals() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(new ArrayList<>());
        assertFalse(orderRequestDto.equals(null));
    }

    @Test
    void testEquals2() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(new ArrayList<>());
        assertFalse(orderRequestDto.equals("Different type to OrderRequestDto"));
    }

    @Test
    void testEquals3() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(new ArrayList<>());
        assertTrue(orderRequestDto.equals(orderRequestDto));
        int expectedHashCodeResult = orderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderRequestDto.hashCode());
    }

    @Test
    void testEquals4() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(new ArrayList<>());

        OrderRequestDto orderRequestDto1 = new OrderRequestDto();
        orderRequestDto1.setOrders(new ArrayList<>());
        assertTrue(orderRequestDto.equals(orderRequestDto1));
        int expectedHashCodeResult = orderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderRequestDto1.hashCode());
    }

    @Test
    void testEquals5() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(itemOrderRequestDtoList);

        OrderRequestDto orderRequestDto1 = new OrderRequestDto();
        orderRequestDto1.setOrders(new ArrayList<>());
        assertFalse(orderRequestDto.equals(orderRequestDto1));
    }

    @Test
    void testEquals6() {
        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrders(itemOrderRequestDtoList);

        ItemOrderRequestDto itemOrderRequestDto1 = new ItemOrderRequestDto();
        itemOrderRequestDto1.setBookId(123L);
        itemOrderRequestDto1.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList1 = new ArrayList<>();
        itemOrderRequestDtoList1.add(itemOrderRequestDto1);

        OrderRequestDto orderRequestDto1 = new OrderRequestDto();
        orderRequestDto1.setOrders(itemOrderRequestDtoList1);
        assertTrue(orderRequestDto.equals(orderRequestDto1));
        int expectedHashCodeResult = orderRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, orderRequestDto1.hashCode());
    }
}

