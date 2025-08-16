package com.froilan.synectix.repository.company.inventory;


import com.froilan.synectix.model.inventory.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {

    /**
     * Finds a warehouse by its UUID.
     *
     * @param uuid the UUID of the warehouse
     * @return an Optional containing the warehouse if found, or empty if not found
     */
    Optional<Warehouse> findByUuid(UUID uuid);

    /**
     * Checks if a warehouse exists by its UUID.
     *
     * @param uuid the UUID of the warehouse
     * @return true if a warehouse with the given UUID exists, false otherwise
     */
    boolean existsByUuid(UUID uuid);

}
