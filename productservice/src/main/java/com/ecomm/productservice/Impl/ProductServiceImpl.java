package com.ecomm.productservice.Impl;

import com.ecomm.productservice.Repository.ProductRepository;
import com.ecomm.productservice.entity.*;
import com.ecomm.productservice.exception.InsufficientStockException;
import com.ecomm.productservice.exception.ProductNotFoundException;
import com.ecomm.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRecord productRecord) {
        var save = productRepository.save(productMapper.toProduct(productRecord));
        return save.getId();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::fromProduct)
                .toList();

    }

    @Override
    public ProductResponse getSingleProduct(Integer id) {
        Product singleProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found with given id"));
        return ProductMapper.fromProduct(singleProduct);
    }

    @Override
    public Product updateProduct(Integer id) {
        return null;
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> requestedProductIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .collect(Collectors.toList());

        // 2. Fetch products from the database
        List<Product> storedProducts = productRepository.findAllById(requestedProductIds);

        // 3. Check if all requested products exist
        if (storedProducts.size() != requestedProductIds.size()) {
            throw new ProductNotFoundException("Some products in the request do not exist in the database.");
        }

        // 4. Map product ID to requested quantity
        Map<Integer, Double> requestedQtyMap = request.stream()
                .collect(Collectors.toMap(ProductPurchaseRequest::productId, ProductPurchaseRequest::quantity));

        List<ProductPurchaseResponse> purchaseResponses = new ArrayList<>();
        // 5. Check stock availability and update quantities
        for (Product product : storedProducts) {
            var requestedQty = requestedQtyMap.get(product.getId());

            if (product.getAvailableQty()<requestedQty) {
                throw new InsufficientStockException("Product ID: " + product.getId() + " has insufficient stock.");
            }

            // Reduce available quantity
            product.setAvailableQty(product.getAvailableQty()-requestedQty);
            productRepository.save(product);
           purchaseResponses.add(productMapper.toProductPurchaseResponse(product,requestedQty));

        }
        return purchaseResponses;

        // 6. Save updated products back to the database
    }
}
