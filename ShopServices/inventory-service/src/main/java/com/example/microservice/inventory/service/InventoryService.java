package com.example.microservice.inventory.service;

import com.example.microservice.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String stuCode, Integer quantity) {
        return inventoryRepository.existsByStuCodeAndQuantityGreaterThanEqual(stuCode, quantity);
    }
}
