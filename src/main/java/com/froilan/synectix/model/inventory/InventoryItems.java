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

import com.froilan.synectix.model.Company;

@Entity()
@Table(name = "inventory_item", uniqueConstraints = @UniqueConstraint(columnNames = {"inventory_item_uuid", "warehouse_uuid", "product_uuid"}))
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
    @Column(columnDefinition = "quantity_on_hand", nullable = false, name = "quantity_on_hand", precision = 15, scale = 3)
    private Float quantityOnHand;

    @Getter
    @Setter
    @Column(columnDefinition = "quantity_on_reserved", nullable = false, name = "quantity_on_reserved", precision = 15, scale = 3)
    private Float quantityOnReserved;

    @Getter
    @Setter
    @Column(columnDefinition = "reorder_point", nullable = false, name = "reorder_point", precision = 15, scale = 3)
    private Float reorderPoint;

    @Getter
    @Setter
    @Column(columnDefinition = "minimum_stock", nullable = false, name = "minimum_stock", precision = 15, scale = 3)
    private Float minimumStock;

    @Getter
    @Setter
    @Column(columnDefinition = "maximum_stock", nullable = false, name = "maximum_stock", precision = 15, scale = 3)
    private Float maximumStock;

    @Getter
    @Setter
    @Column(columnDefinition = "unit_cost", nullable = false, name = "unit_cost", precision = 15, scale = 3)
    private Float unitCost;

    @Getter
    @Setter
    @Column(columnDefinition = "last_count", nullable = false, name = "last_count")
    private Instant lastCount;

}
