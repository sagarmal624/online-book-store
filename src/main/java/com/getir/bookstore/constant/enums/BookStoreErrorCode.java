package com.getir.bookstore.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum BookStoreErrorCode {

    INTERNAL_SERVER_ERROR(1000,"Internal server error."),
    BOOK_NOT_FOUND(1001,"Book Record is not found in DB"),
    ORDER_NOT_FOUND(1002,"Order Record is not found in DB"),
    CUSTOMER_NOT_FOUND(1003,"Customer Record is not found in DB"),
    STOCK_NOT_FOUND(1004,"Stock is not found in DB for this book id"),
    UN_AUTHORIZATION(1003,"Un Authorized."),
    STOCK_RECORD_NOT_FOUND(1005,"Stock record not found."),
    STOCK_ERROR(1006,"Sorry, we do not have enough book in stock."),
    FIELD_VALIDATION_ERROR(1007,"Field validation error."),
    UNIQUE_RECORD_VALIDATION_ERROR(1008,"Record is exist with this details so Field validation error.");
    private int code;
    private String message;
}
