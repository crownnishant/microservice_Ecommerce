package com.ecomm.orderservice.exception;

public class PaymentFailedException extends RuntimeException {

    public PaymentFailedException(String msg){
        super(msg);
    }
}
