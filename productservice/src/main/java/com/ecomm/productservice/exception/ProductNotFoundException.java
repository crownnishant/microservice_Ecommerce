package com.ecomm.productservice.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String s) {
        super("product not found on server");
    }
}
