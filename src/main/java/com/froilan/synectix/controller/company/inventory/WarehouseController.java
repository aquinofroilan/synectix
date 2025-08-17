package com.froilan.synectix.controller.company.inventory;

import com.froilan.synectix.model.dto.request.inventory.WarehouseCreateBody;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/company/inventory/warehouse")
public class WarehouseController {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public String createWarehouse(@Valid @RequestBody WarehouseCreateBody warehouseCreateBody) {
        logger.info("Creating a new warehouse");
        // Logic to create a new warehouse
        return "Warehouse created successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{uuid}")
    public String updateWarehouse(@PathVariable("uuid") String uuid, @Valid @RequestBody WarehouseCreateBody warehouseCreateBody) {
        logger.info("Updating an existing warehouse with UUID: {}", uuid);
        // Logic to update an existing warehouse
        return "Warehouse updated successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{uuid}")
    public String deleteWarehouse() {
        logger.info("Deleting a warehouse");
        // Logic to delete a warehouse
        return "Warehouse deleted successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public String getWarehouse(@PathVariable("uuid") String uuid) {
        logger.info("Retrieving warehouse details for UUID: {}", uuid);
        // Logic to retrieve warehouse details
        return "Warehouse details retrieved successfully";
    }

}
