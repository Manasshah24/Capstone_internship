package com.example.product_ms_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories // Enables MongoDB Repositories

public class ProductMs1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProductMs1Application.class, args);
	}

}
