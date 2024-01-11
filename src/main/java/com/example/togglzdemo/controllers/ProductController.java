/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.controllers;

import com.example.togglzdemo.config.FeatureFlags;
import com.example.togglzdemo.models.Product;
import com.example.togglzdemo.models.ProductDTO;
import com.example.togglzdemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: ProductController.java, v 0.1 2024-01-08 1:39 pm Ken Harvey Dela Cruz $$
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FeatureManager featureManager;

    @GetMapping("product/foo")
    public Product doFoo() {
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("This is a nice product");
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setPrice(4.99);

        if (featureManager.isActive(FeatureFlags.NAME_UPDATE)){
            product.setName("(ON SALE) Product 1");
        }

        if (featureManager.isActive(FeatureFlags.PRICE_SALE)){
            product.setPrice(2.99);
        }

        return productRepository.save(product);
    }

    @GetMapping("/product/v1")
    public ResponseEntity<?> getProductsV1() {
        if (featureManager.isActive(FeatureFlags.API_VERSION_1)) {
            // Fetch products as per API Version 1 specification
            List<Product> products = productRepository.findAll();
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.status(HttpStatus.GONE).body("API Version 1 is not available.");
        }
    }

    @GetMapping("/product/v2")
    public ResponseEntity<?> getProductsV2() {
        if (featureManager.isActive(FeatureFlags.API_VERSION_2)) {
            // Fetch products and transform them as per API Version 2 specification
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTO = products.stream()
                    .map(this::convertToProductDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.status(HttpStatus.GONE).body("API Version 2 is not available.");
        }
    }

    private ProductDTO convertToProductDTO(Product product) {
        // Assume Product has id, name, and price fields
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}