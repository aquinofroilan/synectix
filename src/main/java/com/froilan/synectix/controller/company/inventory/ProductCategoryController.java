package com.froilan.synectix.controller.company.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.froilan.synectix.model.dto.request.inventory.ProductCategoryCreateBody;
import com.froilan.synectix.model.dto.response.product.category.ProductCategoryDTO;
import com.froilan.synectix.service.inventory.ProductCategoryManagementService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company/inventory/product-categories")
public class ProductCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
    private final ProductCategoryManagementService productCategoryManagementService;
    private final JWTClaims jwtClaims;

    public ProductCategoryController(ProductCategoryManagementService productCategoryManagementService, JWTClaims jwtClaims) {
        this.productCategoryManagementService = productCategoryManagementService;
        this.jwtClaims = jwtClaims;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Object>> createProductCategory(@Valid @RequestBody ProductCategoryCreateBody request) {
        String userUuid = jwtClaims.getCurrentUserId();
        String companyUuid = jwtClaims.getCurrentCompanyUuid();
        Integer productCategoryId = productCategoryManagementService.createProductCategory(request, companyUuid);
        logger.info("Product category creation request. endpoint=/api/company/inventory/product-categories, userUuid={}, companyUuid={}, productCategoryId={}",
                userUuid,
                companyUuid,
                productCategoryId);
        return ResponseEntity.ok().body(Map.of("status", "success", "productCategoryId", productCategoryId));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> updateProductCategory(@PathVariable String id, @Valid @RequestBody ProductCategoryCreateBody request) {
        logger.info("Updating product category with ID: {}", id);
        String companyUuid = jwtClaims.getCurrentCompanyUuid();
        Integer productCategoryId = productCategoryManagementService.updateProductCategory(Integer.valueOf(id), request, companyUuid);
        return ResponseEntity.ok().body(Map.of("status", "success", "productCategoryId", productCategoryId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteProductCategory(@PathVariable String id) {
        String companyUuid = jwtClaims.getCurrentCompanyUuid();
        Boolean result = productCategoryManagementService.deleteProductCategory(Integer.valueOf(id), companyUuid);
        logger.info("Deleted product category with ID. endpoint=/api/company/inventory/product-categories/{}, companyUuid={}, result={}",
                id,
                companyUuid,
                result);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Map<String, Object>> listProductCategories() {
        String companyUuid = jwtClaims.getCurrentCompanyUuid();
        List<ProductCategoryDTO> productCategories = productCategoryManagementService.getProductCategories(companyUuid);
        logger.info("Listing product categories for company UUID. endpoint=/api/company/inventory/product-categories, companyUuid={}, total={}",
                companyUuid,
                productCategories.size());
        return ResponseEntity.ok().body(Map.of("status", "success", "data", productCategories));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Map<String, Object>> getProductCategoryById(@PathVariable String id) {
        ProductCategoryDTO productCategory = productCategoryManagementService.getProductCategory(Integer.valueOf(id));
        logger.info("Retrieved product category with ID. endpoint=/api/company/inventory/product-categories/{}, productCategoryId={}",
                id,
                productCategory.getProductCategoryId());
        return ResponseEntity.ok().body(Map.of("status", "success", "data", productCategory));
    }
}
