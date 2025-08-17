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

    @NotBlank(message = "Warehouse code cannot be blank")
    private String warehouseCode;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotBlank(message = "Address Line 1 cannot be blank")
    private String addressLine1;

    @NotBlank(message = "Address Line 2 cannot be blank")
    private String addressLine2;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State province cannot be blank")
    private String stateProvince;

    @NotBlank(message = "Postal Code cannot be blank")
    private String postalCode;

    @NotBlank(message = "Warehouse type cannot be blank")
    private String warehouseType;

    @NotBlank(message = "Capacity limit cannot be blank")
    private double capacityLimit;

    @NotBlank(message = "Capacity unit cannot be blank")
    private String capacityUnit;

    @Builder.Default
    private boolean isActive = true;

    private String createdBy;
    private String updatedBy;
}
