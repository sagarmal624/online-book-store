package com.getir.bookstore.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class ItemOrderRequestDto {
    @NotNull(message = "Book Id Can't be null")
    private Long bookId;
    @NotNull(message = "Quantity Can't be null")
    @Min(value = 1, message = "Quantity Should Be greater than 0.")
    private Integer quantity;
}
