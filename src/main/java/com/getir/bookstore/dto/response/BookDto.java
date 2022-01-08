package com.getir.bookstore.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto extends BaseDto {
    private String title;
    private String author;
    private String description;
    private BigDecimal price;
}
