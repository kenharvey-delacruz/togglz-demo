/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.controllers;

import com.example.togglzdemo.config.FeatureFlags;
import com.example.togglzdemo.models.Booking;
import com.example.togglzdemo.models.Product;
import com.example.togglzdemo.models.ProductDTO;
import com.example.togglzdemo.repositories.BookingRepository;
import com.example.togglzdemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.togglz.core.manager.FeatureManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: BookingController.java, v 0.1 2024-01-10 8:44 pm Ken Harvey Dela Cruz $$
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FeatureManager featureManager;

    @GetMapping("/foo")
    public Booking doFoo() {
        Booking booking = new Booking();
        booking.setCustomerName("Ken");
        booking.setCheckInDate(LocalDateTime.now());
        booking.setCheckOutDate(LocalDateTime.now());
        booking.setPrice(20.00);
        booking.setSpecialOfferApplied(false);

        return bookingRepository.save(booking);
    }

    @PostMapping("/add")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        // Perform any necessary validation or business logic here

        Booking newBooking = bookingRepository.save(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }


    // API Version 1 - Basic Booking Information
    @GetMapping("/show/v1")
    public ResponseEntity<?> getBookingsV1() {
        if (featureManager.isActive(FeatureFlags.API_VERSION_1)) {
            List<Booking> bookings = bookingRepository.findAll();
            return ResponseEntity.ok(bookings);
        } else {
            return ResponseEntity.status(HttpStatus.GONE).body("API Version 1 is not available.");
        }
    }

    // API Version 2 - Bookings with Special Offers
    @GetMapping("/show/v2")
    public ResponseEntity<?> getBookingsV2() {
        if (featureManager.isActive(FeatureFlags.API_VERSION_2)) {
            List<Booking> bookings = bookingRepository.findAll();
            bookings.forEach(this::applySpecialOffers);
            return ResponseEntity.ok(bookings);
        } else {
            return ResponseEntity.status(HttpStatus.GONE).body("API Version 2 is not available.");
        }
    }


    @GetMapping("/show")
    public ResponseEntity<?> getBookingsV3() {
        if (featureManager.isActive(FeatureFlags.SPECIAL_BOOKINGS)) {
            List<Booking> bookings = bookingRepository.findAll();
            bookings.forEach(this::applySpecialOffers);
            return ResponseEntity.ok(bookings);
        } else {
            List<Booking> bookings = bookingRepository.findAll();
            return ResponseEntity.ok(bookings);
        }
    }

    private void applySpecialOffers(Booking booking) {
        // Apply special offers based on certain conditions
        // For example, apply a discount for bookings longer than a week
        if (DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate()) > 7) {
            booking.setPrice(booking.getPrice() * 0.9); // 10% discount
            booking.setSpecialOfferApplied(true);
        }
    }
}
