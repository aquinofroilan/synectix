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
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inventory_transaction_uuid", updatable = false, nullable = false)
    private UUID inventoryTransactionUuid;

    @Getter
    @Setter
    @Column(nullable = false, name = "transaction_type", length = 100)
    private String transactionType;

    /* Reason for the inventory transaction.
     * This field is used to specify the reason for the transaction, such as "Purchase", "Sale", "Adjustment", etc.
     * It helps in tracking and auditing inventory movements.
     */
    @Getter
    @Setter
    @Column( nullable = false, name = "transaction_reason", length = 100)
    private String transactionReason;

    /* Inventory transaction is associated with a specific product.
     * This relationship is established using a foreign key constraint.
     * The foreign key references the primary key of the Product entity.
     * This ensures that each inventory transaction is linked to a valid product in the database.
     */
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_warehouse_uuid", nullable = false)
    private Warehouse warehouse;

    /* Inventory transaction is associated with a specific product.
     * This relationship is established using a foreign key constraint.
     * The foreign key references the primary key of the Product entity.
     * This ensures that each inventory transaction is linked to a valid product in the database.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "quantity", precision = 15, scale = 2)
    private Float quantity;

    /* Inventory transaction is associated with a specific product.
     * This relationship is established using a foreign key constraint.
     * The foreign key references the primary key of the Product entity.
     * This ensures that each inventory transaction is linked to a valid product in the database.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "unit_cost", precision = 15, scale = 2)
    private Float unitCost;

    /* Inventory transaction is associated with a specific product.
     * This relationship is established using a foreign key constraint.
     * The foreign key references the primary key of the Product entity.
     * This ensures that each inventory transaction is linked to a valid product in the database.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "total_cost", precision = 15, scale = 2)
    private Float totalCost;

    /* The date and time when the inventory transaction occurred.
     * This field is automatically set to the current timestamp when the record is created.
     * It helps in tracking when each inventory movement took place.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "transaction_date")
    private Instant transactionDate;

    /* Reference type for the inventory transaction.
     * This field is used to specify the type of reference associated with the transaction, such as "Order", "Invoice", etc.
     * It helps in linking the transaction to other relevant records in the system.
     */
    @Getter
    @Setter
    @Column(name = "reference_type", length = 50)
    private String referenceType;

    /* Reference ID for the inventory transaction.
     * This field is used to specify the ID of the reference associated with the transaction, such as the order ID or invoice ID.
     * It helps in linking the transaction to other relevant records in the system.
     */
    @Getter
    @Setter
    @Column(name = "reference_id", length = 50)
    private String referenceId;

    /* Reference number for the inventory transaction.
     * This field is used to specify the number of the reference associated with the transaction, such as the order number or invoice number.
     * It helps in linking the transaction to other relevant records in the system.
     */
    @Getter
    @Setter
    @Column(name = "reference_number", length = 50)
    private String referenceNumber;

    /* The running balance of the inventory after the transaction.
     * This field is used to keep track of the current inventory level after each transaction.
     * It helps in maintaining accurate inventory records and prevents stockouts or overstocking.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "running_balance", precision = 15, scale = 2)
    private Float runningBalance;

    /* The user who performed the inventory transaction.
     * This relationship is established using a foreign key constraint.
     * The foreign key references the primary key of the User entity.
     * This ensures that each inventory transaction is linked to a valid user in the database.
     */
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_uuid", nullable = false)
    private User user;

    /* Additional notes or comments about the inventory transaction.
     * This field is used to provide any extra information or context about the transaction.
     * It can be useful for auditing purposes or for providing explanations for certain inventory movements.
     */
    @Getter
    @Setter
    @Column(name = "notes", length = 50)
    private String notes;

    /* Lot number for the inventory item involved in the transaction.
     * This field is used to specify the lot number of the product, if applicable.
     * It helps in tracking and managing inventory based on lot numbers, which can be important for quality control and recalls.
     */
    @Getter
    @Setter
    @Column(name = "lot_number", length = 50)
    private String lotNumber;

    /* Serial number for the inventory item involved in the transaction.
     * This field is used to specify the serial number of the product, if applicable.
     * It helps in tracking and managing inventory based on serial numbers, which can be important for warranty and service purposes.
     */
    @Getter
    @Setter
    @Column(name = "serialNumber", length = 100)
    private String serialNumber;

    /* Expiration date for the inventory item involved in the transaction.
     * This field is used to specify the expiration date of the product, if applicable.
     * It helps in managing inventory based on expiration dates, which can be important for perishable goods and regulatory compliance.
     */
    @Getter
    @Setter
    @Column(name = "expiration_date", nullable = false)
    private Instant expirationDate;

    /* Location code within the warehouse where the inventory item is stored.
     * This field is used to specify the location code of the product, if applicable.
     * It helps in quickly locating and retrieving inventory items within the warehouse.
     */
    @Getter
    @Setter
    @Column(name = "location_code", length = 50)
    private String locationCode;

    /* The timestamp when the inventory transaction record was created.
     * This field is automatically set to the current timestamp when the record is created.
     * It helps in tracking when each inventory transaction was recorded in the system.
     */
    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    /* The timestamp when the inventory transaction record was last updated.
     * This field is automatically updated to the current timestamp whenever the record is modified.
     * It helps in tracking when each inventory transaction was last changed in the system.
     */
    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

}
