package com.ecomm.orderservice.feignclient;

import com.ecomm.orderservice.DTO.PaymentRequest;
import com.ecomm.orderservice.DTO.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {

    @PostMapping
    PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest);
}
