package com.getir.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
    @Column(unique = true)
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal price;
}
