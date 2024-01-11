/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.repositories;

import com.example.togglzdemo.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: BookingRepository.java, v 0.1 2024-01-10 8:45 pm Ken Harvey Dela Cruz $$
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
}