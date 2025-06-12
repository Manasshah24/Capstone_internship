package com.example.order_ms_2;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients(basePackages = "com.example.order_ms_2.feign")
public class OrderMs2Application {
	public static void main(String[] args) {
		SpringApplication.run(OrderMs2Application.class, args);
	}
}