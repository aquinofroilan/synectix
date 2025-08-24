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

import com.froilan.synectix.config.security.jwt.JWTClaims;
import com.froilan.synectix.model.dto.request.inventory.ProductCreateBody;
import com.froilan.synectix.service.inventory.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company/inventory/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final JWTClaims jwtClaims;

    public ProductController(ProductService productService, JWTClaims jwtClaims) {
        this.productService = productService;
        this.jwtClaims = jwtClaims;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public String createProduct(@Valid @RequestBody ProductCreateBody productCreateBody) {
        logger.info("Creating a new product");
        this.productService.createProduct(productCreateBody);
        return "Product created successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public String updateProduct() {
        logger.info("Updating an existing product");
        // Logic to update an existing product
        return "Product updated successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{uuid}")
    public String deleteProduct() {
        logger.info("Deleting a product");
        // Logic to delete a product
        return "Product deleted successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public String getProduct(@PathVariable("uuid") String uuid) {
        logger.info("Retrieving product details for UUID: {}", uuid);
        // Logic to retrieve product details
        return "Product details retrieved successfully";
    }

}
