package com.froilan.synectix.model.dto.request.inventory;

import com.froilan.synectix.model.enums.product.DimensionUnit;
import com.froilan.synectix.model.enums.product.ProductType;
import com.froilan.synectix.model.enums.product.UnitMeasure;
import com.froilan.synectix.model.enums.product.WeightUnit;
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
    private String productDescription;


    @NotBlank(message = "Product category ID cannot be blank")
    private Integer productCategoryId;

    @NotBlank(message = "Brand cannot be blank")
    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    @NotBlank(message = "Model cannot be blank")
    @Size(max = 100, message = "Model must not exceed 100 characters")
    private String model;

    @NotBlank(message = "Unit measure cannot be blank")
    private ProductType productType;

    @NotBlank(message = "Unit measure cannot be blank")
    private UnitMeasure unitMeasure;

    private Float baseCost;

    private Float sellingPrice;

    private Float weight;

    @NotBlank(message = "Weight unit cannot be blank")
    private WeightUnit weightUnit;

    private Float dimensionsLength;

    private Float dimensionsWidth;

    private Float dimensionsHeight;

    @NotBlank(message = "Dimension unit cannot be blank")
    private DimensionUnit dimensionUnit;

    @NotBlank(message = "Barcode cannot be blank")
    private String barcode;

    @NotBlank(message = "QR code cannot be blank")
    private String qrCode;

    @Builder.Default
    private Float minimumStockLevel = 0.0f;

    @Builder.Default
    private Float reorderPoint = 0.0f;

    @Builder.Default
    private Float reorderQuantity = 0.0f;

    @Builder.Default
    private boolean isActive = true;

    @Builder.Default
    private boolean isSerialized = false;

    @Builder.Default
    private boolean isLotTracked = false;

    @Builder.Default
    private boolean expirationTracking = false;

    @Builder.Default
    private boolean isDeleted = false;
}
