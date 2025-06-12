package com.example.api_gateway_ms_5.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-client", url = "http://localhost:8085")
@RequestMapping("/fallback")
public interface CircuitBreakerService {

    @GetMapping("/products")
    @CircuitBreaker(name = "productCircuitBreaker", fallbackMethod = "productFallback")
    String getProductData();

    @GetMapping("/inventory")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "inventoryFallback")
    String getInventoryData();

    @GetMapping("/orders")
    @CircuitBreaker(name = "orderCircuitBreaker", fallbackMethod = "orderFallback")
    String getOrderData();

    @GetMapping("/notification")
    @CircuitBreaker(name = "notificationCircuitBreaker", fallbackMethod = "notificationFallback")
    String getNotificationData();

    // Fallback Methods
    default String productFallback(Exception e) {
        return "Product Service is temporarily unavailable. Please try again later.";
    }

    default String inventoryFallback(Exception e) {
        return "Inventory Service is temporarily unavailable. Please try again later.";
    }

    default String orderFallback(Exception e) {
        return "Order Service is temporarily unavailable. Please try again later.";
    }

    default String notificationFallback(Exception e) {
        return "Notification Service is temporarily unavailable. Please try again later.";
    }
}

