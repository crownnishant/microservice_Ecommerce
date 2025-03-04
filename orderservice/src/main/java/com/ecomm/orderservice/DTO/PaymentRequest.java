package com.ecomm.orderservice.DTO;

import com.ecomm.orderservice.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Integer orderId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
