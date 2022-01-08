package com.getir.bookstore.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
public class Book extends BaseEntity {
    private String title;
    private String author;
    private String description;
    private BigDecimal price;
}
