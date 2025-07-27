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

@Entity()
@Table(name = "inventory", uniqueConstraints = @UniqueConstraint(columnNames = {"warehouse_uuid"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    /**
     * The UUID of the company.
     * This variable is used to display the company's UUID in the application.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "inventory_uuid", updatable = false, nullable = false)
    private UUID inventoryUuid;

    @Getter
    @Setter
    @OneToOne(mappedBy = "inventory", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_uuid", referencedColumnName = "warehouse_uuid", nullable = false)
    private Product product;

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
