package com.getir.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String fieldName;
    private String message;
//    private Object fieldValue;
}
