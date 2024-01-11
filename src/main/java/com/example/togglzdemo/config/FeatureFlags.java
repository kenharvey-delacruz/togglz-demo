/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.togglzdemo.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

/**
 * @author Ken Harvey Dela Cruz
 * @version $Id: FeatureFlags.java, v 0.1 2024-01-08 1:52 pm Ken Harvey Dela Cruz $$
 */
public enum FeatureFlags implements Feature {

    @Label("Price Sale for Holiday Season 2023")
    PRICE_SALE,

    @Label("Name update")
    NAME_UPDATE,

    // Adding API version feature flags
    @Label("Enable API Version 1")
    API_VERSION_1,

    @Label("Enable API Version 2")
    API_VERSION_2;

}