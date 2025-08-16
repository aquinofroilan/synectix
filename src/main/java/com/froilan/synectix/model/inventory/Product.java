package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.Company;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"product_uuid", "product_name", "sku"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "product_uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Getter
    @Setter
    @OneToOne(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Company company;

    @Getter
    @Setter
    @OneToOne(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_category_id", referencedColumnName = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "product_name", length = 50, columnDefinition = "VARCHAR(50)")
    private String productName;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "sku", length = 50, columnDefinition = "VARCHAR(50)")
    private String sku;

    @Getter
    @Setter
    @Column(nullable = false, name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @Getter
    @Setter
    @Column(nullable = false, name = "productBrand", columnDefinition = "VARCHAR(100)")
    private String productBrand;

    @Getter
    @Setter
    @Column(nullable = false, name = "productModel", columnDefinition = "VARCHAR(100)")
    private String productModel;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive = true;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "product_type", length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'PHYSICAL'")
    private String productType = "PHYSICAL";

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "unit_of_measure", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'EACH'")
    private String unitOfMeasure = "EACH";

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "base_cost", columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private BigDecimal baseCost = BigDecimal.ZERO;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "selling_price", columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private BigDecimal sellingPrice = BigDecimal.ZERO;

    @Getter
    @Setter
    @Column(name = "weight", columnDefinition = "DECIMAL(10,3)")
    private BigDecimal weight;

    @Getter
    @Setter
    @Column(name = "weight_unit", length = 10, columnDefinition = "VARCHAR(10)")
    private String weightUnit;

    @Getter
    @Setter
    @Column(name = "dimensions_length", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal dimensionsLength;

    @Getter
    @Setter
    @Column(name = "dimensions_width", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal dimensionsWidth;

    @Getter
    @Setter
    @Column(name = "dimensions_height", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal dimensionsHeight;

    @Getter
    @Setter
    @Column(name = "dimension_unit", length = 10, columnDefinition = "VARCHAR(10)")
    private String dimensionUnit;

    @Getter
    @Setter
    @Column(name = "barcode", length = 100, columnDefinition = "VARCHAR(100)")
    private String barcode;

    @Getter
    @Setter
    @Column(name = "qr_code", length = 255, columnDefinition = "VARCHAR(255)")
    private String qrCode;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "minimum_stock_level", columnDefinition = "DECIMAL(15,2) DEFAULT 0")
    private BigDecimal minimumStockLevel = BigDecimal.ZERO;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "reorder_point", columnDefinition = "DECIMAL(15,2) DEFAULT 0")
    private BigDecimal reorderPoint = BigDecimal.ZERO;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "reorder_quantity", columnDefinition = "DECIMAL(15,2) DEFAULT 0")
    private BigDecimal reorderQuantity = BigDecimal.ZERO;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_serialized", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isSerialized = false;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_lot_tracked", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isLotTracked = false;

    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "expiration_tracking", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean expirationTracking = false;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant updatedAt;

    @Getter
    @Setter
    @Column(nullable = false, name = "created_by", columnDefinition = "BIGINT")
    private Long createdBy;

    @Getter
    @Setter
    @Column(nullable = false, name = "updated_by", columnDefinition = "BIGINT")
    private Long updatedBy;

}
