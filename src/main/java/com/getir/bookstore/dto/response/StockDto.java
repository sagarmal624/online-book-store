package com.getir.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto extends BaseDto {
    private Long bookId;
    private Integer quantity;
    private Long stockId;
}
