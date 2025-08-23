package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.User;
import com.froilan.synectix.model.enums.product.DimensionUnit;
import com.froilan.synectix.model.enums.product.ProductType;
import com.froilan.synectix.model.enums.product.UnitMeasure;
import com.froilan.synectix.model.enums.product.WeightUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = { "product_uuid" }))
public class Product {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, name = "product_uuid")
    private UUID productUuid;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "sku", length = 100, columnDefinition = "VARCHAR(100)")
    private String sku;

    @Getter
    @Setter
    @Column(nullable = false, name = "product_name", length = 100, columnDefinition = "VARCHAR(100)")
    private String productName;

    @Setter
    @Getter
    @Column(nullable = false, name = "product_description", columnDefinition = "VARCHAR(255)")
    private String productDescription;

    @Setter
    @Getter
    @Column(nullable = false, name = "brand", columnDefinition = "VARCHAR(100)")
    private String brand;

    @Setter
    @Getter
    @Column(nullable = false, name = "model", columnDefinition = "VARCHAR(100)")
    private String model;

    @Setter
    @Getter
    @Column(nullable = false, name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Setter
    @Getter
    @Column(nullable = false, name = "unit_measure")
    @Enumerated(EnumType.STRING)
    private UnitMeasure unitMeasure;

    @Getter
    @Setter
    @Column(nullable = false, name = "base_cost", columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private Float baseCost = 0f;

    @Getter
    @Setter
    @Column(nullable = false, name = "selling_price", columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private Float sellingPrice = 0f;

    @Getter
    @Setter
    @Column(nullable = false, name = "weight", columnDefinition = "DECIMAL(10,3) DEFAULT 0.000")
    private Float weight = 0f;

    @Getter
    @Setter
    @Column(nullable = false, name = "weight_unit")
    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;

    @Getter
    @Setter
    @Column(nullable = false, name = "dimensions_length", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Float dimensionsLength = 0f;

    @Getter
    @Setter
    @Column(nullable = false, name = "dimensions_width", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Float dimensionsWidth = 0f;

    @Getter
    @Setter
    @Column(nullable = false, name = "dimensions_height", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Float dimensionsHeight = 0f;

    @Setter
    @Getter
    @Column(nullable = false, name = "dimension_unit")
    @Enumerated(EnumType.STRING)
    private DimensionUnit dimensionUnit;

    @Setter
    @Getter
    @Column(nullable = false, name = "barcode", columnDefinition = "VARCHAR(255")
    private String barcode;

    @Setter
    @Getter
    @Column(nullable = false, name = "qr_code", columnDefinition = "VARCHAR(255)")
    private String qrCode;

    @Getter
    @Setter
    @Column(nullable = false, name = "minimum_stock_level", columnDefinition = "DECIMAL(15,2) DEFAULT 0")
    private Float minimumStockLevel = 0f;

    @Getter
    @Setter
    @Column
    (nullable = false, name = "reorder_point", columnDefinition = "DECIMAL(15,2) DEFAULT 0")
    private Float reorderPoint = 0f;

    @Getter
    @Setter
    @Column(nullable = false, name = "reorder_quantity", columnDefinition = "DECIMAL(15,2) DEFAULT 0")
    private Float reorderQuantity = 0f;

    @Setter
    @Getter
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive = true;

    @Setter
    @Getter
    @Column(nullable = false, name = "is_serialized", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isSerialized = false;

    @Setter
    @Getter
    @Column(nullable = false, name = "is_lot_tracked", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isLotTracked = false;

    @Setter
    @Getter
    @Column(nullable = false, name = "expiration_tracking", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean expirationTracking = false;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_user_uuid_created_by", nullable = false)
    private User createdBy;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_user_uuid_updated_by", nullable = false)
    private User updatedBy;

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
}
