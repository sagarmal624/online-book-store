package com.getir.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Getter
@Setter
@Entity
public class ItemOrder extends BaseEntity {
    @OneToOne
    private Book book;
    private Integer quantity;
}
