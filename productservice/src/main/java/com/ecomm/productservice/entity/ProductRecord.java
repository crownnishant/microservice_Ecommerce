package com.ecomm.productservice.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRecord(

         Integer id,

         @NotNull(message = "product name is required")
         String name,

         @NotNull(message = "product description is required")
         String description,

         @Positive(message = "available qty should be positive")
         double availableQty,

         @Positive(message = "price should be positive")
         BigDecimal price,

         @NotNull(message = "price category is required")
         Integer categoryId
) {
}
