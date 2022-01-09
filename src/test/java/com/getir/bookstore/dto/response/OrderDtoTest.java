package com.getir.bookstore.dto.response;

import com.getir.bookstore.constant.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrderDtoTest {
    @Test
    void testConstructor() {
        OrderDto actualOrderDto = new OrderDto();
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualOrderDto.setAmount(valueOfResult);
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualOrderDto.setCreatedDate(ofResult);
        ArrayList<ItemOrderDto> itemOrderDtoList = new ArrayList<>();
        actualOrderDto.setItems(itemOrderDtoList);
        actualOrderDto.setStatus(OrderStatus.IN_PROGRESS);
        assertSame(valueOfResult, actualOrderDto.getAmount());
        assertSame(ofResult, actualOrderDto.getCreatedDate());
        assertSame(itemOrderDtoList, actualOrderDto.getItems());
        assertEquals(OrderStatus.IN_PROGRESS, actualOrderDto.getStatus());
        assertEquals("OrderDto(status=IN_PROGRESS, amount=42, createdDate=0001-01-01T01:01, items=[])",
                actualOrderDto.toString());
    }
}

