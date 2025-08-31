package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.enums.product.DimensionUnit;
import com.froilan.synectix.model.enums.product.ProductType;
import com.froilan.synectix.model.enums.product.UnitMeasure;
import com.froilan.synectix.model.enums.product.WeightUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"product_uuid"}))
public class Product {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, name = "product_uuid")
    private UUID productUuid;

    @Getter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company_uuid", nullable = false)
    private Company company;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_product_category_id", nullable = false)
    private ProductCategory productCategory;

    /**
     * The SKU (Stock Keeping Unit) of the product.
     * This variable is used to uniquely identify the product in the inventory system.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "sku", length = 100)
    private String sku;

    /**
     * The name of the product.
     * This variable is used to display the product's name in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "product_name", length = 100)
    private String productName;

    /**
     * The description of the product.
     * This variable is used to provide additional information about the product.
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "product_description")
    private String productDescription;

    /**
     * The brand of the product.
     * This variable is used to display the product's brand in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "brand", length = 100)
    private String brand;

    /**
     * The model of the product.
     * This variable is used to display the product's model in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "model", length = 100)
    private String model;

    /**
     * The type of the product.
     * This variable is used to categorize the product in the inventory system.
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    /**
     * The unit of measure for the product.
     * This variable is used to define how the product is measured (e.g., pieces, kilograms, liters).
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "unit_measure")
    @Enumerated(EnumType.STRING)
    private UnitMeasure unitMeasure;

    /**
     * The base cost of the product.
     * This variable is used to store the cost price of the product in the inventory system.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "base_cost", precision = 15, scale = 2)
    private BigDecimal baseCost = BigDecimal.ZERO;

    /**
     * The selling price of the product.
     * This variable is used to store the retail price of the product in the inventory system.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "selling_price", precision = 15, scale = 2)
    private BigDecimal sellingPrice = BigDecimal.ZERO;

    /**
     * The weight of the product.
     * This variable is used to store the weight of the product for shipping and handling purposes.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "weight", precision = 10, scale = 3)
    private BigDecimal weight = BigDecimal.ZERO;

    /**
     * The unit of weight for the product.
     * This variable is used to define the unit of measurement for the product's weight (e.g., kg, lb).
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "weight_unit")
    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;

    /**
     * The dimensions of the product (length, width, height).
     * These variables are used to store the physical dimensions of the product for storage and shipping purposes.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "dimensions_length", precision = 10, scale = 2)
    private BigDecimal dimensionsLength = BigDecimal.ZERO;

    /**
     * The width of the product.
     * This variable is used to store the width dimension of the product.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "dimensions_width", precision = 10, scale = 2)
    private BigDecimal dimensionsWidth = BigDecimal.ZERO;

    /**
     * The height of the product.
     * This variable is used to store the height dimension of the product.
     */


    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "dimensions_height", precision = 10, scale = 2)
    private BigDecimal dimensionsHeight = BigDecimal.ZERO;

    /**
     * The unit of measurement for the product's dimensions.
     * This variable is used to define the unit of measurement for the product's dimensions (e.g., cm, in).
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "dimension_unit")
    @Enumerated(EnumType.STRING)
    private DimensionUnit dimensionUnit;

    /**
     * The barcode of the product.
     * This variable is used to store the barcode value for scanning and identification purposes.
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "barcode")
    private String barcode;

    /**
     * The QR code of the product.
     * This variable is used to store the QR code value for scanning and identification purposes.
     */
    @Setter
    @Getter
    @Column(nullable = false, name = "qr_code")
    private String qrCode;

    /**
     * The minimum stock level for the product.
     * This variable is used to trigger reordering when the stock level falls below this threshold.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "minimum_stock_level", precision = 15, scale = 2)
    private BigDecimal minimumStockLevel = BigDecimal.ZERO;

    /**
     * The reorder point for the product.
     * This variable is used to determine when to reorder the product based on current stock levels.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "reorder_point", precision = 15, scale = 2)
    private BigDecimal reorderPoint = BigDecimal.ZERO;

    /**
     * The reorder quantity for the product.
     * This variable is used to specify the quantity to reorder when the stock level reaches the reorder point.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "reorder_quantity", precision = 15, scale = 2)
    private BigDecimal reorderQuantity = BigDecimal.ZERO;

    /**
     * Indicates whether the product is deleted.
     * This variable is used to display the product's deletion status in the application.
     */
    @Setter
    @Getter
    @Builder.Default
    @Column(nullable = false, name = "is_active")
    private Boolean isActive = true;

    /**
     * Indicates whether the product is deleted.
     * This variable is used to display the product's deletion status in the application.
     */
    @Setter
    @Getter
    @Builder.Default
    @Column(nullable = false, name = "is_serialized")
    private Boolean isSerialized = false;

    /**
     * Indicates whether the product is lot tracked.
     * This variable is used to display the product's lot tracking status in the application.
     */
    @Setter
    @Getter
    @Builder.Default
    @Column(nullable = false, name = "is_lot_tracked")
    private Boolean isLotTracked = false;

    /**
     * Indicates whether the product has expiration tracking.
     * This variable is used to display the product's expiration tracking status in the application.
     */
    @Setter
    @Getter
    @Builder.Default
    @Column(nullable = false, name = "expiration_tracking")
    private Boolean expirationTracking = false;

    /**
     * The user who created the product.
     * This variable is used to track which user created the product in the system.
     */
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_user_uuid_created_by", nullable = false)
    private User createdBy;

    /**
     * The user who last updated the product.
     * This variable is used to track which user last updated the product in the system.
     */
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_user_uuid_updated_by", nullable = false)
    private User updatedBy;

    /**
     * The timestamp when the product was created.
     * This variable is used to display the product's creation time in the application.
     */
    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    /**
     * The timestamp when the product was last updated.
     * This variable is used to display the product's last update time in the application.
     */
    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

}
