package com.ecomm.productservice.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String s) {
        super("stock not sufficient !!");
    }
}
