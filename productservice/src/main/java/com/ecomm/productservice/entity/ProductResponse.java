package com.ecomm.productservice.entity;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,
        String name,
        String description,
        double availableQty,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription

) {
}
