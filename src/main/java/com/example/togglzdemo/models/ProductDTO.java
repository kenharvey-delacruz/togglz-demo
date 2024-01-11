/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: ProductDTO.java, v 0.1 2024-01-08 5:43 pm Ken Harvey Dela Cruz $$
 */
@Getter
@Setter
public class ProductDTO {

    // Getters and Setters
    private Long id;
    private String name;
    private String description;
    private Double price;
    // You can include other fields as needed or exclude certain fields

    // Constructor
    public ProductDTO(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
