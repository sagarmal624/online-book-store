package com.getir.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String fieldName;
    private String message;
//    private Object fieldValue;
}
