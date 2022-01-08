package com.getir.bookstore.dto.request;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class OrderRequestDto {
    @Valid
    List<ItemOrderRequestDto> orders;
}
