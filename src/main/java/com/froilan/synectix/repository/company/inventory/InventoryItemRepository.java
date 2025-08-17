package com.froilan.synectix.repository.company.inventory;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.froilan.synectix.model.inventory.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
    /**
     * Finds an inventory item by its UUID.
     *
     * @param itemUuid the UUID of the inventory item
     * @return the inventory item if found, or null if not found
     */
    InventoryItem findByItemUuid(UUID itemUuid);

    /**
     * Checks if an inventory item exists by its UUID.
     *
     * @param itemUuid the UUID of the inventory item
     * @return true if the inventory item exists, false otherwise
     */
    boolean existsByItemUuid(UUID itemUuid);
}
