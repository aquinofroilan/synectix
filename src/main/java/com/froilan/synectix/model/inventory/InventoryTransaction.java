package com.froilan.synectix.model.inventory;

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
@Table(name = "inventory_transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"inventory_uuid",
    "warehouse_uuid",
    "product_uuid"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransaction {

    // Unique identifier for the inventory transaction record.
    // This is a UUID that is generated automatically.
    // It is used to uniquely identify each inventory transaction record in the database.
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "inventory_transaction_uuid", updatable = false, nullable = false)
    private UUID inventoryTransactionUuid;

    @Getter
    @Setter
    @Column(columnDefinition = "transaction_type", nullable = false, name = "notes")
    private String transactionType;

    @Getter
    @Setter
    @OneToOne(mappedBy = "inventoryTransaction", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_warehouse_uuid_source", referencedColumnName = "warehouse_uuid", nullable = false)
    private Warehouse warehouse;

    @Getter
    @Setter
    @OneToOne(mappedBy = "inventoryTransaction", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_warehouse_uuid_destination", referencedColumnName = "warehouse_uuid", nullable = false)
    private Warehouse destinationWarehouse;

    @Getter
    @Setter
    @Column(columnDefinition = "quantity", nullable = false, name = "quantity", precision = 15, scale = 3)
    private Float quantity;

    @Getter
    @Setter
    @Column(columnDefinition = "unit_cost", nullable = false, name = "unit_cost", precision = 15, scale = 2)
    private Float unitCost;

    @Getter
    @Setter
    @Column(columnDefinition = "transaction_date", nullable = false, name = "transaction_date")
    private Instant transactionDate;

    @Getter
    @Setter
    @Column(columnDefinition = "reference_number", name = "reference_number", length = 50)
    private String referenceNumber;

    @Getter
    @Setter
    @OneToOne(mappedBy = "inventoryTransaction", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_user_uuid", referencedColumnName = "user_uuid", nullable = false)
    private User user;

    @Getter
    @Setter
    @Column(columnDefinition = "notes", name = "notes")
    private String notes;

}
