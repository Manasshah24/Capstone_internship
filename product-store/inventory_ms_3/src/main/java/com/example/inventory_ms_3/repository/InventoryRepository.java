package com.example.inventory_ms_3.repository;

import com.example.inventory_ms_3.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}
