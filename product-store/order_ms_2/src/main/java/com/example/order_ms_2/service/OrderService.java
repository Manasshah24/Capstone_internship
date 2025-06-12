package com.example.order_ms_2.service;

import com.example.common.NotificationDto;
import com.example.order_ms_2.dto.ProductResponse;
import com.example.order_ms_2.feign.InventoryServiceClient;
import com.example.order_ms_2.feign.ProductClient;
import com.example.order_ms_2.model.Order;
import com.example.order_ms_2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private static final String TOPIC_NAME = "order-topic";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private InventoryServiceClient inventoryClient;

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    @Autowired
    public OrderService(KafkaTemplate<String, NotificationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Order placeOrder(String productId, int quantity, String userId, String userEmail) {
        // 1. Check product availability
        boolean isAvailable = productClient.isProductAvailable(productId).getBody();
        if (!isAvailable) {
            throw new RuntimeException("Product not available");
        }

        // 2. Check inventory stock
        boolean stockAvailable = inventoryClient.checkAndDeductStock(productId, quantity).getBody();
        if (!stockAvailable) {
            throw new RuntimeException("Not enough stock available");
        }

        // 3. Fetch product price
        ProductResponse product = productClient.getProductById(productId).getBody();
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // 4. Calculate total amount
        double totalAmount = product.getPrice() * quantity;

        // 5. Save order
        Order order = new Order();
        order.setUserId(userId);
        order.setUserEmail(userEmail);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setProductId(Integer.parseInt(productId));
        order.setQuantity(quantity);

        // ✅ Ensures order is saved successfully before sending Kafka event
        Order savedOrder = orderRepository.save(order);

        // 6. Send Kafka event **only if order is successfully saved**
        NotificationDto notificationDto = new NotificationDto(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getUserEmail(),
                savedOrder.getOrderDate(),
                savedOrder.getTotalAmount(),
                savedOrder.getProductId(),
                savedOrder.getQuantity()
        );
        kafkaTemplate.send(TOPIC_NAME, notificationDto);
        System.out.println("Sent");
        return savedOrder;
    }
}
