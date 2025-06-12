package com.example.product_ms_1.repository;




import com.example.product_ms_1.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // Custom query methods can be added here
}