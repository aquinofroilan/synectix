package com.froilan.synectix.model.dto.request.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateBody {
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, max = 50, message = "Product name must be between 3 and 50 characters")
    private String productName;

    @NotBlank(message = "SKU cannot be blank")
    @Size(min = 3, max = 50, message = "SKU must be between 3 and 50 characters")
    private String sku;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;


    @NotBlank(message = "Product category ID cannot be blank")
    private String productCategoryId; // Assuming this is the ID of the ProductCategory

    @NotBlank(message = "Warehouse ID cannot be blank")
    private String warehouseId; // Assuming this is the ID of the Warehouse

    @Builder.Default
    private boolean isActive = true; // Default to active if not specified

    @Builder.Default
    private boolean isDeleted = false; // Default to not deleted if not specified
}
