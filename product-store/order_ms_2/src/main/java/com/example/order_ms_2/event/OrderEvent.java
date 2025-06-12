package com.example.order_ms_2.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String orderId;
    private String productId;
    private int quantity;
    private String status; // PENDING, CONFIRMED, CANCELLED
}
