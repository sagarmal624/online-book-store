package com.getir.bookstore.dto.response;

import lombok.Data;


@Data
public class ItemOrderDto extends BaseDto {
    private Integer quantity;
    private BookDto book;

}
