package com.froilan.synectix.controller.company.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/inventory/product-category")
public class ProductCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createProductCategory() {
        logger.info("Creating a new product category");
        // Logic to create a new product category
        return "Product category created successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update")
    public String updateProductCategory() {
        logger.info("Updating an existing product category");
        // Logic to update an existing product category
        return "Product category updated successfully";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteProductCategory(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID provided for deletion");
            throw new IllegalArgumentException("Invalid ID provided for deletion");
        }
        logger.info("Deleting product category with ID: {}", id);
        // Logic to delete a product category by ID
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public String listProductCategories() {
        logger.info("Listing all product categories");
        // Logic to list all product categories
        return "List of product categories";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{id}")
    public String getProductCategoryById(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID provided for retrieval");
            throw new IllegalArgumentException("Invalid ID provided for retrieval");
        }
        logger.info("Retrieving product category with ID: {}", id);
        // Logic to retrieve a product category by ID
        return "Product category details for ID: " + id;
    }
}
