package com.froilan.synectix.controller.company.inventory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/inventory")
public class InventoryClass {
    private static final Logger logger = LoggerFactory.getLogger(InventoryClass.class);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public String getAllInventory() {
        logger.info("Fetching all inventory records");
        // Logic to fetch all inventory records
        return "All inventory records fetched successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public String getInventoryByUuid() {
        logger.info("Fetching inventory record by UUID");
        // Logic to fetch inventory record by UUID
        return "Inventory record fetched successfully by UUID";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createInventory() {
        logger.info("Creating a new inventory record");
        // Logic to create a new inventory record
        return "Inventory record created successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public String updateInventory() {
        logger.info("Updating an existing inventory record");
        // Logic to update an existing inventory record
        return "Inventory record updated successfully";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/delete/{uuid}")
    public String deleteInventory() {
        logger.info("Deleting an inventory record by UUID");
        // Logic to delete an inventory record by UUID
        return "Inventory record deleted successfully";
    }
}
