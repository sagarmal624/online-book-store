package com.getir.bookstore.domain;

import java.math.BigDecimal;


public interface Statistic {
    int getTotalOrder();

    BigDecimal getTotalAmount();

    int getTotalPurchaseBookCount();

    int getMonth();
}
