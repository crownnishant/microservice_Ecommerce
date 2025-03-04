package com.ecomm.productservice.entity;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRecord productRecord) {

        if(productRecord==null){
            return null;
        }
        return Product.builder()
                .id(productRecord.id())
                .name(productRecord.name())
                .description(productRecord.description())
                .availableQty(productRecord.availableQty())
                .price(productRecord.price())
                .category(Category.builder().id(productRecord.categoryId()).build())
                .build();
    }

    public static ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQty(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double qty){
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                qty
        );
    }
}
