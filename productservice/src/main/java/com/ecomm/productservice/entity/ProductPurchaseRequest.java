package com.ecomm.productservice.entity;

import jakarta.validation.constraints.NotNull;


public record ProductPurchaseRequest(

        @NotNull(message = "product is mandatory")
        Integer productId,

        @NotNull(message = "quantity is mandatory")
        double quantity

) {
}
