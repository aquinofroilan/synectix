package com.froilan.synectix.controller.company.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/inventory/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/create")
    public String createProduct() {
        logger.info("Creating a new product");
        // Logic to create a new product
        return "Product created successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update")
    public String updateProduct() {
        logger.info("Updating an existing product");
        // Logic to update an existing product
        return "Product updated successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/delete")
    public String deleteProduct() {
        logger.info("Deleting a product");
        // Logic to delete a product
        return "Product deleted successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/get")
    public String getProduct() {
        logger.info("Retrieving product details");
        // Logic to retrieve product details
        return "Product details retrieved successfully";
    }

}
