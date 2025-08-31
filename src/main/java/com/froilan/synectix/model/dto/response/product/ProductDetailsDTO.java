package com.froilan.synectix.model.dto.response.product;

import java.math.BigDecimal;
import java.time.Instant;

import com.froilan.synectix.model.enums.product.DimensionUnit;
import com.froilan.synectix.model.enums.product.ProductType;
import com.froilan.synectix.model.enums.product.UnitMeasure;
import com.froilan.synectix.model.enums.product.WeightUnit;
import com.froilan.synectix.model.inventory.ProductCategory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetailsDTO {
    private String productUuid;
    private ProductCategory productCategory;
    private String sku;
    private String productName;
    private String productDescription;
    private String brand;
    private String model;
    private ProductType productType;
    private UnitMeasure unitMeasure;
    private BigDecimal baseCost;
    private BigDecimal sellingPrice;
    private BigDecimal weight;
    private WeightUnit weightUnit;
    private BigDecimal dimensionsLength;
    private BigDecimal dimensionsWidth;
    private BigDecimal dimensionsHeight;
    private DimensionUnit dimensionUnit;
    private String barcode;
    private String qrCode;
    private BigDecimal minimumStockLevel;
    private BigDecimal reorderPoint;
    private BigDecimal reorderQuantity;
    private boolean isActive;
    private boolean isSerialized;
    private boolean isLotTracked;
    private boolean expirationTracking;
    private String createdByUuid;
    private String updatedByUuid;
    private Instant createdAt;
    private Instant updatedAt;
}
