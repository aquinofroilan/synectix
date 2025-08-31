package com.froilan.synectix.model.dto.response.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

import com.froilan.synectix.model.enums.product.WarehouseCapacityUnit;
import com.froilan.synectix.model.enums.product.WarehouseType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseDetailsDTO {
    private UUID warehouseUuid;
    private String warehouseName;
    private String warehouseCode;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private WarehouseType warehouseType;
    private BigDecimal capacityLimit;
    private WarehouseCapacityUnit capacityUnit;
    private Boolean isActive;

    private UUID companyUuid;
    private String companyName;

    private Integer countryId;
    private String countryName;

    private UUID createdByUserUuid;
    private String createdByUsername;
    private UUID updatedByUserUuid;
    private String updatedByUsername;
}
