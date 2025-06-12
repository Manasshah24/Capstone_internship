package com.example.inventory_ms_3.controller;

import com.example.inventory_ms_3.model.Inventory;
import com.example.inventory_ms_3.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Manually add inventory for a product
    @PostMapping("/{productId}/{quantity}")
    public ResponseEntity<Inventory> addInventory(@PathVariable String productId, @PathVariable int quantity) {
        Inventory updatedInventory = inventoryService.addInventory(productId, quantity);
        return ResponseEntity.ok(updatedInventory);
    }

    // Check if stock is available and update it when an order is placed
    @GetMapping("/{productId}/{quantity}")
    public ResponseEntity<Boolean> checkAndDeductStock(@PathVariable String productId, @PathVariable int quantity) {
        boolean isAvailable = inventoryService.checkAndDeductStock(productId, quantity);
        return ResponseEntity.ok(isAvailable);
    }

    // Get current stock of a product
    @GetMapping("/{productId}")
    public ResponseEntity<Integer> getStock(@PathVariable String productId) {
        int stock = inventoryService.getProductStock(productId);
        return ResponseEntity.ok(stock);
    }
}
