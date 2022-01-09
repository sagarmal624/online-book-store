package com.getir.bookstore.domain;

import com.getir.bookstore.constant.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(SpringExtension.class)
class OrderTest {
    @Test
    void testConstructor() {
        Order actualOrder = new Order();
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualOrder.setAmount(valueOfResult);
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setUsername("janedoe");
        actualOrder.setCustomer(customer);
        ArrayList<ItemOrder> itemOrderList = new ArrayList<>();
        actualOrder.setItemOrders(itemOrderList);
        actualOrder.setStatus(OrderStatus.IN_PROGRESS);
        assertSame(valueOfResult, actualOrder.getAmount());
        assertSame(customer, actualOrder.getCustomer());
        assertSame(itemOrderList, actualOrder.getItemOrders());
        assertEquals(OrderStatus.IN_PROGRESS, actualOrder.getStatus());
    }
}

