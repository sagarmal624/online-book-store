package com.getir.bookstore.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@Builder
public class StockRequestDto {
    private Long bookId;
    @NotNull
    private Integer quantity;
}
