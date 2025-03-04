package com.ecomm.productservice.conroller;

import com.ecomm.productservice.entity.ProductPurchaseRequest;
import com.ecomm.productservice.entity.ProductPurchaseResponse;
import com.ecomm.productservice.entity.ProductRecord;
import com.ecomm.productservice.entity.ProductResponse;
import com.ecomm.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRecord productRecord){
        return ResponseEntity.ok(productService.createProduct(productRecord));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request){
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getSingleProduct(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getSingleProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }


}
