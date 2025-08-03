package com.froilan.synectix.controller.company.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.froilan.synectix.model.dto.request.inventory.ProductCategoryCreateBody;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/company/inventory/product-categories")
public class ProductCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProductCategory(@Valid @RequestBody ProductCategoryCreateBody request) {
        logger.info("Creating a new product category");
        // Logic to create
        return "Product category created successfully";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProductCategory(@PathVariable String id, @Valid @RequestBody ProductCategoryCreateBody request) {
        logger.info("Updating product category with ID: {}", id);
        // Logic to update
        return "Product category updated successfully";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductCategory(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            logger.error("Invalid ID provided for deletion");
            throw new IllegalArgumentException("Invalid ID provided for deletion");
        }
        logger.info("Deleting product category with ID: {}", id);
        // Logic to delete
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String listProductCategories() {
        logger.info("Listing all product categories");
        // Logic to list
        return "List of product categories";
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getProductCategoryById(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            logger.error("Invalid ID provided for retrieval");
            throw new IllegalArgumentException("Invalid ID provided for retrieval");
        }
        logger.info("Retrieving product category with ID: {}", id);
        // Logic to retrieve
        return "Product category details for ID: " + id;
    }
}
