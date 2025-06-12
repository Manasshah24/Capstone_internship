package com.example.inventory_ms_3.service;
import com.example.inventory_ms_3.model.Inventory;
import com.example.inventory_ms_3.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Add or update inventory for a product
    public Inventory addInventory(String productId, int quantity) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElse(new Inventory(productId, 0));  // If product not found, create new

        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    // Check if stock is available and update stock
    @Transactional
    public boolean checkAndDeductStock(String productId, int orderQuantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);

        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();

            if (inventory.getQuantity() >= orderQuantity) {
                inventory.setQuantity(inventory.getQuantity() - orderQuantity);
                inventoryRepository.save(inventory);
                return true;  // Stock available
            }
        }
        return false;  // Not enough stock
    }

    // Get current inventory quantity for a product
    public int getProductStock(String productId) {
        return inventoryRepository.findById(productId)
                .map(Inventory::getQuantity)
                .orElse(0);
    }
}
