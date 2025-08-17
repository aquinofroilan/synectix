package com.froilan.synectix.service.inventory;

import com.froilan.synectix.config.security.jwt.JWTUtil;
import com.froilan.synectix.model.dto.request.inventory.WarehouseCreateBody;
import com.froilan.synectix.model.inventory.Warehouse;
import org.springframework.stereotype.Service;

@Service
public class WarehouseManagementService {
    private final JWTUtil jwtUtil;

    public WarehouseManagementService(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public void createWarehouse(WarehouseCreateBody newWarehouse) {
        Warehouse warehouse = Warehouse.builder()
                .warehouseName(newWarehouse.getWarehouseName())
                .addressLine1(newWarehouse.getAddressLine1())
                .addressLine2(newWarehouse.getAddressLine2())
                .city(newWarehouse.getCity())
                .stateProvince(newWarehouse.getStateProvince())
                .postalCode(newWarehouse.getPostalCode())
                .warehouseType(newWarehouse.getWarehouseType())
                .capacityLimit(newWarehouse.getCapacityLimit())
                .capacityUnit(newWarehouse.getCapacityUnit())
                .isActive(newWarehouse.isActive())
//                .createdBy(jwtUtil.getUuidFromToken().toString())
//                .updatedBy(jwtUtil.getUuidFromToken().toString())
                .build();
    }
}
