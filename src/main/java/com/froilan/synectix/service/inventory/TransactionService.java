package com.froilan.synectix.service.inventory;

import org.springframework.stereotype.Service;

import com.froilan.synectix.model.dto.request.company.InventoryTransactionCreateBody;
import com.froilan.synectix.model.inventory.InventoryItem;
import com.froilan.synectix.model.inventory.InventoryTransaction;
import com.froilan.synectix.repository.company.inventory.InventoryItemRepository;
import com.froilan.synectix.repository.company.inventory.InventoryTransactionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TransactionService {
    @PersistenceContext
    private EntityManager entityManager;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final InventoryItemRepository inventoryItemRepository;

    public TransactionService(InventoryTransactionRepository inventoryTransactionRepository, InventoryItemRepository inventoryItemRepository) {
        this.inventoryTransactionRepository = inventoryTransactionRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public InventoryItem createInventoryItem(InventoryTransactionCreateBody createBody) {
        InventoryItem inventoryItem = new InventoryItem();
        InventoryTransaction transaction = new InventoryTransaction();

        inventoryItem.setQuantityOnHand(createBody.getQuantityOnHand());
        inventoryItem.setQuantityAllocated(createBody.getQuantityAllocated());
        inventoryItem.setQuantityAvailable(createBody.getQuantityAvailable());
        inventoryItem.setQuantityOnOrder(createBody.getQuantityOnOrder());
        inventoryItem.setAverageCost(createBody.getAverageCost());
        inventoryItem.setLastCost(createBody.getLastCost());
        inventoryItem.setLocationCode(createBody.getLocationCode());
        inventoryItem.setLotNumber(createBody.getLotNumber());
        inventoryItem.setSerialNumber(createBody.getSerialNumber());
        inventoryItem.setExpirationDate(createBody.getExpirationDate());
        inventoryItem.setLastCountedDate(createBody.getLastCountedDate());
        inventoryItem.setLastMovementDate(createBody.getLastMovementDate());
        transaction.setTransactionType(createBody.getTransactionType());
        transaction.setTransactionReason(createBody.getTransactionReason());
        transaction.setQuantity(createBody.getQuantity());
        transaction.setUnitCost(createBody.getUnitCosts());
        transaction.setTotalCost(createBody.getTotalCosts());
        transaction.setNotes(createBody.getNotes());

        entityManager.persist(inventoryItem);
        entityManager.persist(transaction);

        inventoryTransactionRepository.save(transaction);
        inventoryItemRepository.save(inventoryItem);

        return inventoryItem;
    }
}
