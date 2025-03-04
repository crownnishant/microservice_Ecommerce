package com.ecomm.customerservice.exception;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String customer) {
        super("customer not found on the server");
    }
}
