package com.example.api_gateway_ms_5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/products")
    public String productFallback() {
        return "Product Service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/inventory")
    public String inventoryFallback() {
        return "Inventory Service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/orders")
    public String orderFallback() {
        return "Order Service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/notification")
    public String notificationFallback() {
        return "Notification Service is temporarily unavailable. Please try again later.";
    }
}
