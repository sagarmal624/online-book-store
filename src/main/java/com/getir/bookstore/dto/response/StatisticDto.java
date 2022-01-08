package com.getir.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StatisticDto {
    private String month;
    private int totalOrder;
    private BigDecimal totalAmount;
    private int totalPurchaseBookCount;
}
