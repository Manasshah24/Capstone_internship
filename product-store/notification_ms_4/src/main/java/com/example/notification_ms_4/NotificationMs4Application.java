package com.example.notification_ms_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NotificationMs4Application {
	public static void main(String[] args) {
		SpringApplication.run(NotificationMs4Application.class, args);
	}
}
