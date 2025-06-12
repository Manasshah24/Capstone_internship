package com.example.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long id;
    private String userId;
    private String userEmail;
    private LocalDateTime orderDate;
    private double totalAmount;
    private int productId;
    private int quantity;
}

