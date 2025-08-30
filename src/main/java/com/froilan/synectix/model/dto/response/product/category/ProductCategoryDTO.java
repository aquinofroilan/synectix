package com.froilan.synectix.model.dto.response.product.category;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDTO {
    private Integer productCategoryId;
    private String productCategoryCode;
    private String productCategoryName;
    private String description;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private String companyUuid;
}
