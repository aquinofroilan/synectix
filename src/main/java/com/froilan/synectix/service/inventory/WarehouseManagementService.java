package com.froilan.synectix.service.inventory;

import com.froilan.synectix.model.dto.request.inventory.WarehouseCreateBody;
import com.froilan.synectix.repository.company.inventory.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseManagementService {
    private final WarehouseRepository warehouseRepository;
    public WarehouseManagementService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public void createWarehouse(WarehouseCreateBody newWarehouse) {
        // Logic to create a new warehouse
        // This would typically involve saving the newWarehouse object to the database
        // using the warehouseRepository.
        WarehouseRepository warehouse = WarehouseRepository.builder()
                .warehouseName(newWarehouse.getWarehouseName())
                .location(newWarehouse.getLocation())
                .isActive(newWarehouse.isActive())
                .build();
    }
}
