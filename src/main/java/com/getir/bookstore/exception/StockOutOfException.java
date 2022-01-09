package com.getir.bookstore.exception;

public class StockOutOfException extends RuntimeException{
    public StockOutOfException(String message){
        super(message);
    }
}
