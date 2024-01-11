/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.repositories;

import com.example.togglzdemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: ProductRepository.java, v 0.1 2024-01-08 1:38 pm Ken Harvey Dela Cruz $$
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}