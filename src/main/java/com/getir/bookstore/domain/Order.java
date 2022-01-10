package com.getir.bookstore.domain;

import com.getir.bookstore.constant.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bookOrder")
public class Order extends BaseEntity {
    @OneToOne
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemOrder> itemOrders;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal amount;
    private Integer totalQuantity;
}
