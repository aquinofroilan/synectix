package com.froilan.synectix.repository.company.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.froilan.synectix.model.inventory.InventoryTransaction;

import java.util.UUID;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, UUID> {
    /**
     * Finds an inventory transaction by its UUID.
     * @param inventoryTransactionUuid the UUID of the inventory transaction
     * @return the inventory transaction if found, or null if not found
     */
    InventoryTransaction findByInventoryTransactionUuid(UUID inventoryTransactionUuid);

    /**
     * Checks if an inventory transaction exists by its UUID.
     * @param inventoryTransactionUuid the UUID of the inventory transaction
     * @return true if an inventory transaction with the given UUID exists, false otherwise
     */
    boolean existsByInventoryTransactionUuid(UUID inventoryTransactionUuid);

}
