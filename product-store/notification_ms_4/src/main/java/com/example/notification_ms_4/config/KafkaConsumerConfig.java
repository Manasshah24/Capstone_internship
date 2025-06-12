package com.example.notification_ms_4.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public NewTopic createOrderTopic() {
        return new NewTopic("order-topic", 1, (short) 1);
    }
}