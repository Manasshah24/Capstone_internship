package com.example.order_ms_2.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    private static final String TOPIC_NAME = "order-topic";

    // Creates the topic dynamically (No CLI required)
    @Bean
    public NewTopic createOrderTopic() {
        return new NewTopic(TOPIC_NAME, 1, (short) 1);
    }

}