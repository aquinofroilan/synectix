package com.froilan.synectix.repository.company.inventory;

import com.froilan.synectix.model.inventory.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
    /**
     * Finds an inventory item by its UUID.
     *
     * @param inventoryItemUuid the UUID of the inventory item
     * @return the inventory item if found, or null if not found
     */
    InventoryItem findByInventoryItemUuid(UUID inventoryItemUuid);

    /**
     * Checks if an inventory item exists by its UUID.
     *
     * @param inventoryItemUuid the UUID of the inventory item
     * @return true if the inventory item exists, false otherwise
     */
    boolean existsByInventoryItemUuid(UUID inventoryItemUuid);
}
