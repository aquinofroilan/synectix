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
    @OneToOne(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private ProductCategory productCategory;

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
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "created_by", columnDefinition = "VARCHAR(50)")
    private String createdBy;

    @Getter
    @Setter
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "updated_by", columnDefinition = "VARCHAR(50)")
    private String updatedBy;

}
