package com.ecomm.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/{productId}/availability/{qty}")
    boolean checkProductAvailability(@PathVariable Integer productId, @PathVariable Double qty);

    @GetMapping("/{productId}/price")
    BigDecimal getPrice(@PathVariable Integer productId);

}
