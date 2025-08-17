package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity()
@Table(name = "inventory_transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"inventory_transaction_uuid"}))
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
    @Column(name = "inventory_transaction_uuid", updatable = false, nullable = false)
    private UUID inventoryTransactionUuid;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(100)", nullable = false, name = "transaction_type", length = 100)
    private String transactionType;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(100)", nullable = false, name = "transaction_reason", length = 100)
    private String transactionReason;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_warehouse_uuid", nullable = false)
    private Warehouse warehouse;

    @Getter
    @Setter
    @Column(columnDefinition = "DECIMAL", nullable = false, name = "quantity")
    private Float quantity;

    @Getter
    @Setter
    @Column(columnDefinition = "DECIMAL(15,2)", nullable = false, name = "unit_cost")
    private Float unitCost;

    @Getter
    @Setter
    @Column(columnDefinition = "DECIMAL(15,2)", nullable = false, name = "total_cost")
    private Float totalCost;

    @Getter
    @Setter
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, name = "transaction_date")
    private Instant transactionDate;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(50)", name = "reference_type")
    private String referenceType;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(50)", name = "reference_id")
    private String referenceId;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(50)", name = "reference_number", length = 50)
    private String referenceNumber;

    @Getter
    @Setter
    @Column(columnDefinition = "DECIMAL(15,2)", nullable = false, name = "running_balance")
    private Float runningBalance;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_uuid", nullable = false)
    private User user;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(50)", name = "notes", length = 50)
    private String notes;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(50)", name = "lot_number")
    private String lotNumber;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(100)", name = "serialNumber", length = 100)
    private String serialNumber;

    @Getter
    @Setter
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "expiration_date")
    private Instant expirationDate;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(50)", name = "location_code", length = 50)
    private String locationCode;

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
