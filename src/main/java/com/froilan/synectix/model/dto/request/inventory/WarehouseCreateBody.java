package com.froilan.synectix.model.dto.request.inventory;

import com.froilan.synectix.model.enums.product.WarehouseCapacityUnit;
import com.froilan.synectix.model.enums.product.WarehouseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseCreateBody {

    @NotBlank(message = "Warehouse name cannot be blank")
    private String warehouseName;

    @NotBlank(message = "Warehouse code cannot be blank")
    private String warehouseCode;

    private String description;

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

    @NotNull(message = "Country ID cannot be blank")
    private Integer countryId;

    @NotNull(message = "Warehouse type cannot be blank")
    private WarehouseType warehouseType;

    @NotNull(message = "Capacity limit cannot be blank")
    private BigDecimal capacityLimit;

    @NotNull(message = "Capacity unit cannot be blank")
    private WarehouseCapacityUnit capacityUnit;

    @Builder.Default
    private boolean isActive = true;
}
