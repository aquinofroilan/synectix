package com.froilan.synectix.model.inventory;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.froilan.synectix.model.Company;

@Entity()
@Table(name = "inventory_item", uniqueConstraints = @UniqueConstraint(columnNames = {"inventory_item_uuid", "warehouse_uuid", "product_uuid", "company_uuid", "lot_number", "serial_number"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItems {

    /**
     * Unique identifier for the inventory record.
     * This is a UUID that is generated automatically.
     * It is used to uniquely identify each inventory record in the database.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "inventory_item_uuid", updatable = false, nullable = false)
    private UUID inventoryItemUuid;

    @Getter
    @Setter
    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company_uuid", referencedColumnName = "company_uuid", nullable = false)
    private Company company;

    @Getter
    @Setter
    @OneToOne(mappedBy = "inventory", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_warehouse_uuid", referencedColumnName = "warehouse_uuid", nullable = false)
    private Warehouse warehouse;

    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_on_hand", columnDefinition = "DECIMAL(15,3)", precision = 15, scale = 3)
    private Float quantityOnHand;

    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_allocated", columnDefinition = "DECIMAL(15,3)", precision = 15, scale = 3)
    private Float quantityAllocated;

    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_available", columnDefinition = "DECIMAL(15,3)", precision = 15, scale = 3)
    private Float quantityAvailable;

    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_on_order", columnDefinition = "DECIMAL(15,3)", precision = 15, scale = 3)
    private Float quantityOnOrder;

    @Getter
    @Setter
    @Column(nullable = false, name = "average_cost",columnDefinition = "DECIMAL(15,3)",  precision = 15, scale = 3)
    private Float averageCost;

    @Getter
    @Setter
    @Column(nullable = false, name = "last_cost", columnDefinition = "DECIMAL(15,3)", precision = 15, scale = 3)
    private Float lastCost;

    @Getter
    @Setter
    @Column(columnDefinition = "location_code", nullable = false, name = "location_code")
    private String locationCode;

    @Getter
    @Setter
    @Column(columnDefinition = "lot_number", nullable = false, name = "lot_number")
    private String lotNumber;

    @Getter
    @Setter
    @Column(columnDefinition = "serial_number", nullable = false, name = "serial_number")
    private String serialNumber;

    @Getter
    @Setter
    @Column(columnDefinition = "expiration_date", nullable = false, name = "expiration_date")
    private Instant expirationDate;

    @Getter
    @Setter
    @Column(columnDefinition = "last_counted_date", nullable = false, name = "last_counted_date")
    private Instant lastCountedDate;

    @Getter
    @Setter
    @Column(columnDefinition = "last_movement_date", nullable = false, name = "last_movement_date")
    private Instant lastMovementDate;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, name = "created_at")
    private Instant createdAt;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, name = "updated_at")
    private Instant updatedAt;

}
