package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.Company;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

import java.time.Instant;
import java.util.UUID;

@Entity()
@Table(name = "inventory_item", uniqueConstraints = @UniqueConstraint(columnNames = {"inventory_item_uuid",
    "fk_warehouse_uuid", "lot_number", "serial_number"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {

    /**
     * Unique identifier for the inventory record.
     * This is a UUID that is generated automatically.
     * It is used to uniquely identify each inventory record in the database.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inventory_item_uuid", updatable = false, nullable = false)
    private UUID inventoryItemUuid;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company_uuid", nullable = false)
    private Company company;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_warehouse_uuid", referencedColumnName = "warehouse_uuid", nullable = false)
    private Warehouse warehouse;

    /** Inventory item is associated with a specific product.
     * This relationship is established using a foreign key constraint.
     * The foreign key references the primary key of the Product entity.
     * This ensures that each inventory item is linked to a valid product in the database.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_on_hand", precision = 15, scale = 2)
    private Float quantityOnHand;

    /**
     * Quantity of the product that has been allocated for orders but not yet shipped.
     * This helps in managing stock levels and ensuring that allocated stock is not double-booked.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_allocated", precision = 15, scale = 2)
    private Float quantityAllocated;

    /**
     * Quantity of the product that is available for new orders.
     * This is calculated as quantity on hand minus quantity allocated.
     * It provides a clear view of how much stock is actually available for sale.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_available", precision = 15, scale = 2)
    private Float quantityAvailable;

    /**
     * Quantity of the product that has been ordered from suppliers but not yet received.
     * This helps in tracking incoming stock and planning for future inventory needs.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "quantity_on_order", precision = 15, scale = 2)
    private Float quantityOnOrder;

    /**
     * Average cost of the product in inventory.
     * This is used for financial reporting and inventory valuation.
     * It provides insight into the cost trends of the product over time.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "average_cost", precision = 15, scale = 2)
    private Float averageCost;

    /**
     * The last recorded cost of the product.
     * This is useful for tracking cost changes and making pricing decisions.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "last_cost", precision = 15, scale = 2)
    private Float lastCost;

    /**
     * The location code within the warehouse where the product is stored.
     * This helps in quickly locating the product for picking and shipping.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "location_code", length = 50)
    private String locationCode;

    /**
     * The lot number associated with the inventory item.
     * This is important for tracking and managing products that are part of a specific batch or production run.
     * It helps in quality control and recall management if necessary.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "lot_number", length = 100)
    private String lotNumber;

    /**
     * The serial number associated with the inventory item.
     * This is important for tracking individual units of a product, especially for high-value or regulated items.
     * It helps in warranty management and after-sales service.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "serial_number", length = 100)
    private String serialNumber;

    /**
     * The expiration date of the inventory item.
     * This is crucial for managing perishable goods and ensuring that products are sold before they expire.
     * It helps in reducing waste and maintaining product quality.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "expiration_date")
    private Instant expirationDate;

    /**
     * The date when the inventory item was last counted.
     * This is important for maintaining accurate inventory records and identifying discrepancies.
     * Regular counting helps in ensuring that the physical stock matches the recorded stock in the system.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "last_counted_date")
    private Instant lastCountedDate;

    /**
     * The date when the last movement of the inventory item occurred.
     * This includes any additions or subtractions from the inventory, such as receiving new stock or fulfilling orders.
     * Tracking the last movement date helps in understanding inventory turnover and identifying slow-moving items.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "last_movement_date")
    private Instant lastMovementDate;

    /**
     * Timestamp indicating when the inventory item record was created.
     * This is automatically set when the record is first inserted into the database.
     * It provides a historical reference for when the inventory item was added to the system.
     */
    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    /**
     * Timestamp indicating when the inventory item record was last updated.
     * This is automatically updated whenever the record is modified.
     * It helps in tracking changes to the inventory item over time.
     */
    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

}
