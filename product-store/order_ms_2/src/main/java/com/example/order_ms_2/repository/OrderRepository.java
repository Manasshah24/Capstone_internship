package com.example.order_ms_2.repository;


import com.example.order_ms_2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}

