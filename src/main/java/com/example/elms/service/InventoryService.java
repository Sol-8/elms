package com.example.elms.service;

import com.example.elms.model.Inventory;
import com.example.elms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public Long getBookId(Long id) {
        Optional<Inventory> inventoryDetails = inventoryRepository.findById(id);
        return inventoryDetails.map(Inventory::getBookId).orElse(null);
    }

    public Optional<Object> findInventoryByBookId(Long bookId) {
        return Optional.empty();
    }
}
