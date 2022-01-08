package com.getir.bookstore.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageRequestDto {
    @NotNull
    private Integer size;
    @NotNull
    private Integer page;

}
