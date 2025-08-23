package com.froilan.synectix.model.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
