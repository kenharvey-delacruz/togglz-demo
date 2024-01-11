/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: Booking.java, v 0.1 2024-01-10 8:44 pm Ken Harvey Dela Cruz $$
 */
@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Double price;
    private Boolean specialOfferApplied; // Field to indicate if a special offer has been applied

    // Constructors, Getters, Setters...
}
