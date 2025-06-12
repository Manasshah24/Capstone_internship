package com.example.notification_ms_4.service;

import com.example.common.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consumeOrderEvent(NotificationDto notificationDto) {
        System.out.println("Received Order Event: " + notificationDto);
        sendEmail(notificationDto);
    }

    private void sendEmail(NotificationDto notificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("manasshah2424@gmail.com");
        message.setTo(notificationDto.getUserEmail());
        message.setSubject("Order Confirmation - Order ID: " + notificationDto.getId());
        message.setText("Dear Customer,\n\n" +
                "Your order has been placed successfully!\n" +
                "Order Details:\n" +
                "Product ID: " + notificationDto.getProductId() + "\n" +
                "Quantity: " + notificationDto.getQuantity() + "\n" +
                "Total Amount: $" + notificationDto.getTotalAmount() + "\n\n" +
                "Thank you for shopping with us!");

        mailSender.send(message);
        System.out.println("Email sent successfully to: " + notificationDto.getUserEmail());
    }
}