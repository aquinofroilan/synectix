package com.froilan.synectix.controller.company.inventory;

import com.froilan.synectix.config.security.jwt.JWTClaims;
import com.froilan.synectix.model.dto.request.inventory.WarehouseCreateBody;
import com.froilan.synectix.model.dto.response.warehouse.WarehouseDetailsDTO;
import com.froilan.synectix.model.inventory.Warehouse;
import com.froilan.synectix.service.inventory.WarehouseManagementService;

import jakarta.validation.Valid;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/inventory/warehouse")

public class WarehouseController {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
    private final WarehouseManagementService warehouseManagementService;
    private final JWTClaims jwtClaims;

    public WarehouseController(WarehouseManagementService warehouseManagementService, JWTClaims jwtClaims) {
        this.warehouseManagementService = warehouseManagementService;
        this.jwtClaims = jwtClaims;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Map<String, String>> createWarehouse(@Valid @RequestBody WarehouseCreateBody warehouseCreateBody) {
        String userUuid = jwtClaims.getCurrentUserId();
        String companyUuid = jwtClaims.getCurrentCompanyUuid();
        Warehouse result = warehouseManagementService.createWarehouse(warehouseCreateBody, userUuid, companyUuid);
        logger.info("Warehouse creation request. endpoint=/api/company/inventory/warehouse, userUuid={}, companyUuid={}, resultWarehouseUuid={}",
                userUuid,
                companyUuid,
                result.getWarehouseUuid());
        return ResponseEntity.ok().body(Map.of("status", "success", "warehouseUuid", result.getWarehouseUuid().toString()));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{uuid}")
    public ResponseEntity<Map<String, String>> updateWarehouse(@PathVariable("uuid") String uuid,
            @Valid @RequestBody WarehouseCreateBody warehouseCreateBody) {
        String userUuid = jwtClaims.getCurrentUserId();
        String companyUuid = jwtClaims.getCurrentCompanyUuid();
        logger.info("Warehouse update request. endpoint=/api/company/inventory/warehouse, userUuid={}, companyUuid={}, warehouseUuid={}",
                userUuid,
                companyUuid,
                uuid);
        warehouseManagementService.updateWarehouse(uuid, warehouseCreateBody, userUuid, companyUuid);
        return ResponseEntity.ok().body(Map.of("status", "success", "message", "Warehouse updated successfully"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Map<String, String>> deleteWarehouse(@PathVariable("uuid") String uuid) {
        logger.info("Warehouse deletion request. endpoint=/api/company/inventory/warehouse, userUuid={}, companyUuid={}, warehouseUuid={}",
                jwtClaims.getCurrentUserId(),
                jwtClaims.getCurrentCompanyUuid(),
                uuid);
        warehouseManagementService.deleteWarehouse(uuid);
        return ResponseEntity.ok().body(Map.of("status", "success", "message", "Warehouse deleted successfully"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public ResponseEntity<Map<String, Object>> getWarehouse(@PathVariable("uuid") String uuid) {
        logger.info("Warehouse retrieval request. endpoint=/api/company/inventory/warehouse, userUuid={}, companyUuid={}, warehouseUuid={}",
                jwtClaims.getCurrentUserId(),
                jwtClaims.getCurrentCompanyUuid(),
                uuid);
        WarehouseDetailsDTO warehouse = warehouseManagementService.getWarehouse(uuid);
        return ResponseEntity.ok().body(Map.of("status", "success", "warehouse", warehouse));
    }

}
