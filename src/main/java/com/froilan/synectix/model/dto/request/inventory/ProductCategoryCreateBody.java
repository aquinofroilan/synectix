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
public class ProductCategoryCreateBody {
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name cannot be blank")
    private String productCategoryName;

    @Size(min = 3, max = 50, message = "Product category code must be between 3 and 50 characters")
    @NotBlank(message = "Product category code cannot be blank")
    private String productCategoryCode;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    @NotBlank(message = "Description cannot be blank")
    private String description;
}
