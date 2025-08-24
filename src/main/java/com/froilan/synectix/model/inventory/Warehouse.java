package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity()
@Table(name = "warehouse", uniqueConstraints = @UniqueConstraint(columnNames = { "warehouse_uuid" }))
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "warehouse_uuid", updatable = false, nullable = false)
    private UUID warehouseUuid;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "warehouse")
    private Company company;

    @Getter
    @Setter
    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    private InventoryItem inventoryItem;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "warehouse")
    private Set<InventoryTransaction> inventoryTransactions;

    /**
     * The name of the warehouse.
     * This variable is used to display the warehouse's name in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "warehouse_name", length = 100, columnDefinition = "VARCHAR(100)")
    private String warehouseName;

    /**
     * The code of the warehouse.
     * This variable is used to display the warehouse's code in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "warehouse_code", columnDefinition = "VARCHAR(50)")
    private String warehouseCode;

    /**
     * The description of the warehouse.
     * This variable is used to display the warehouse's description in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    /**
     * The address line 1 of the warehouse.
     * This variable is used to display the warehouse's address line 1 in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "address_line_1", columnDefinition = "VARCHAR(255)")
    private String addressLine1;

    /**
     * The address line 2 of the warehouse.
     * This variable is used to display the warehouse's address line 2 in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "address_line_2", columnDefinition = "VARCHAR(255)")
    private String addressLine2;

    /**
     * The city of the warehouse.
     * This variable is used to display the warehouse's city in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "city", columnDefinition = "VARCHAR(100)")
    private String city;

    /**
     * The state or province of the warehouse.
     * This variable is used to display the warehouse's state or province in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "state_province", columnDefinition = "VARCHAR(100)")
    private String stateProvince;

    /**
     * The postal code of the warehouse.
     * This variable is used to display the warehouse's postal code in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "postal_code", columnDefinition = "VARCHAR(20)")
    private String postalCode;

    /**
     * The country of the warehouse.
     * This variable is used to display the warehouse's country in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "warehouse_type", columnDefinition = "VARCHAR(50)")
    private String warehouseType;

    /**
     * Indicates whether the warehouse is active.
     * This variable is used to display the warehouse's active status in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isActive;

    /**
     * The capacity limit of the warehouse.
     * This variable is used to display the warehouse's capacity limit in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "capacity_limit", columnDefinition = "DECIMAL(10, 2)")
    private double capacityLimit;

    /**
     * The capacity unit of the warehouse.
     * This variable is used to display the warehouse's capacity unit in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "capacity_unit", columnDefinition = "VARCHAR(50)")
    private String capacityUnit;

    /**
     * Timestamp when the warehouse was created.
     * This variable is used to display the creation time of the warehouse in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    /**
     * Timestamp when the warehouse was last updated.
     * This variable is used to display the last update time of the warehouse in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    /**
     * Users associated with the warehouse.
     * This variable is used to display the users linked to the warehouse in the application.
     */
    @Getter
    @Setter
    @ManyToMany(mappedBy = "warehouse")
    private Set<User> users;

    /**
     * The user who created the warehouse record.
     * This variable is used to display the creator of the warehouse in the application.
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_uuid", referencedColumnName = "user_uuid")
    private User createdBy;

    /**
     * The user who last updated the warehouse record.
     * This variable is used to display the last updater of the warehouse in the application.
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_uuid", referencedColumnName = "user_uuid")
    private User updatedBy;
}
