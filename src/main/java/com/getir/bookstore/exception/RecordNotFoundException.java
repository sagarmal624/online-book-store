package com.getir.bookstore.exception;

import lombok.Data;

@Data
public class RecordNotFoundException extends RuntimeException {
    private String fieldName;

    public RecordNotFoundException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
