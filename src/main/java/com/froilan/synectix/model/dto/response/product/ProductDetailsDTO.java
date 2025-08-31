package com.froilan.synectix.model.dto.response.product;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetailsDTO {
    private String productUuid;
    private String productCategory;
    private String sku;
    private String productName;
    private String productDescription;
    private String brand;
    private String model;
    private String productType;
    private String unitMeasure;
    private BigDecimal baseCost;
    private BigDecimal sellingPrice;
    private BigDecimal weight;
    private String weightUnit;
    private BigDecimal dimensionsLength;
    private BigDecimal dimensionsWidth;
    private BigDecimal dimensionsHeight;
    private String dimensionUnit;
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
