package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.time.Instant;
import java.util.UUID;

@Entity()
@Table(name = "warehouse", uniqueConstraints = @UniqueConstraint(columnNames = { "warehouse_uuid" }))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "warehouse_uuid", updatable = false, nullable = false)
    private UUID warehouseUuid;

    @Getter
    @Setter
    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company_uuid", referencedColumnName = "company_uuid", nullable = false)
    private Company company;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "warehouse_name", length = 100, columnDefinition = "VARCHAR(100)")
    private String warehouseName;

    @Getter
    @Setter
    @Column(nullable = false, name = "warehouse_code", columnDefinition = "VARCHAR(50)")
    private String warehouseCode;

    @Getter
    @Setter
    @Column(nullable = false, name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @Getter
    @Setter
    @Column(nullable = false, name = "address_line_1", columnDefinition = "VARCHAR(255)")
    private String addressLine1;

    @Getter
    @Setter
    @Column(nullable = false, name = "address_line_2", columnDefinition = "VARCHAR(255)")
    private String addressLine2;

    @Getter
    @Setter
    @Column(nullable = false, name = "city", columnDefinition = "VARCHAR(100)")
    private String city;

    @Getter
    @Setter
    @Column(nullable = false, name = "state_province", columnDefinition = "VARCHAR(100)")
    private String stateProvince;

    @Getter
    @Setter
    @Column(nullable = false, name = "postal_code", columnDefinition = "VARCHAR(20)")
    private String postalCode;

    @Getter
    @Setter
    @Column(nullable = false, name = "warehouse_type", columnDefinition = "VARCHAR(50)")
    private String warehouseType;

    @Getter
    @Setter
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isActive;

    @Getter
    @Setter
    @Column(nullable = false, name = "capacity_limit", columnDefinition = "DECIMAL(10, 2)")
    private double capacityLimit;

    @Getter
    @Setter
    @Column(nullable = false, name = "capacity_unit", columnDefinition = "VARCHAR(50)")
    private String capacityUnit;

    @Getter
    @Setter
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Getter
    @Setter
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    @Getter
    @Setter
    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_user_uuid", referencedColumnName = "user_uuid", nullable = false)
    private User createdBy;

    @Getter
    @Setter
    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_user_uuid", referencedColumnName = "user_uuid", nullable = false)
    private User updatedBy;
}
