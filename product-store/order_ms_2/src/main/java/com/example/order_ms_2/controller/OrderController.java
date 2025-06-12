package com.example.order_ms_2.controller;

import com.example.order_ms_2.model.Order;
import com.example.order_ms_2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{productId}/{quantity}/{email}/{userId}")
    public ResponseEntity<Order> placeOrder(
            @PathVariable String productId,
            @PathVariable int quantity,
            @PathVariable String email,
            @PathVariable String userId) {

        Order order = orderService.placeOrder(productId, quantity, userId, email);
        return ResponseEntity.ok(order);
    }
}
