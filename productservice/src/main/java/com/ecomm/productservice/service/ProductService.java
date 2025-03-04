package com.ecomm.productservice.service;

import com.ecomm.productservice.entity.*;

import java.util.List;

public interface ProductService {

    //create Product
    Integer createProduct(ProductRecord productRecord);
    //get all products
    List<ProductResponse> getAllProducts();
    //get single product
    ProductResponse getSingleProduct(Integer id);

    //update product
    Product updateProduct(Integer id);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

}
