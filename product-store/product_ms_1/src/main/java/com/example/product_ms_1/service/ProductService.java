package com.example.product_ms_1.service;

import com.example.product_ms_1.model.Product;
import com.example.product_ms_1.model.Product;
import com.example.product_ms_1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create or update a product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Check product availability
    public boolean isProductAvailable(String id) {
        return productRepository.findById(id)
                .map(Product::isAvailability) // Uses the isAvailability() method
                .orElse(false);
    }

    // Delete a product
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}