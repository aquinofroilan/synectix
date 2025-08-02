package com.froilan.synectix.controller.company.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
}
