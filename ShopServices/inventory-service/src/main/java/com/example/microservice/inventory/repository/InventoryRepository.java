package com.example.microservice.inventory.repository;

import com.example.microservice.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByStuCodeAndQuantityGreaterThanEqual(String stuCode, Integer quantity);
}
