package com.getir.bookstore.dto.response;

import com.getir.bookstore.constant.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto extends BaseDto {
    private OrderStatus status;
    private BigDecimal amount;
    private LocalDateTime createdDate;
    private List<ItemOrderDto> items;
}
