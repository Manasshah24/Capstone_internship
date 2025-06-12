package com.example.order_ms_2.feign;

import com.example.order_ms_2.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/products/{id}/availability")
    ResponseEntity<Boolean> isProductAvailable(@PathVariable String id);

    @GetMapping("/products/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable String id);
}
