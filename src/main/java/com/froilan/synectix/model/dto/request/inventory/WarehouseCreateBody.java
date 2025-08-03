package com.froilan.synectix.model.dto.request.inventory;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseCreateBody {

    @NotBlank(message = "Warehouse name cannot be blank")
    private String warehouseName;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Builder.Default
    private boolean isActive = true; // Default to active if not specified
}
