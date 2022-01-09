package com.getir.bookstore.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PageRequestDto {
    @NotNull
    @Min(value = 1)
    private Integer size;
    @NotNull
    @Min(value = 0)
    private Integer page;

}
