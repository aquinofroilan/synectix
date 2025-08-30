package com.froilan.synectix.model;

import com.froilan.synectix.model.inventory.InventoryTransaction;
import com.froilan.synectix.model.inventory.Product;
import com.froilan.synectix.model.inventory.Warehouse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
import java.util.Set;
import java.util.UUID;

/**
 * Represents a user in the PostgreSQL database.
 * This class is used to store user information such as username, first name,
 * last name, email, role, and password.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email", "user_uuid",
    "phone_number"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * The UUID of the user.
     * This variable is used to display the user's UUID in the application.
     */
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_uuid", updatable = false, nullable = false)
    private UUID userUuid;

    /**
     * The username of the user.
     * This variable is used to display the user's username in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "username", length = 50)
    private String username;

    /**
     * The first name of the user.
     * This variable is used to display the user's first name in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "first_name", length = 50)
    private String firstName;

    /**
     * The last name of the user.
     * This variable is used to display the user's last name in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "last_name", length = 50)
    private String lastName;

    /**
     * The email of the user.
     * This variable is used to display the user's email in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "email", length = 100)
    private String email;

    /**
     * The phone number of the user.
     * This variable is used to display the user's phone number in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "phone_number", length = 15)
    private String phoneNumber;

    /**
     * The password of the user.
     * This variable is used to display the user's password in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "hashed_password")
    private String hashedPassword;

    /**
     * Indicates whether the user is deleted.
     * This variable is used to display the user's deletion status in the
     * application.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted = false;

    /**
     * Indicates whether the user is active.
     * This variable is used to display the user's active status in the
     * application.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_active")
    private boolean isActive = true;

    /**
     * The timestamp when the user last logged in.
     * This variable is used to display the user's last login time in the
     * application.
     */
    @Getter
    @Setter
    @Column(name = "last_login")
    private Instant lastLogin;

    /**
     * The timestamp when the user was created.
     * This variable is used to display the user's creation time in the
     * application.
     */
    @Getter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at", updatable = false)
    private Instant createdAt;

    /**
     * The timestamp when the user was last updated.
     * This variable is used to display the user's last update time in the
     * application.
     */
    @Getter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

    /**
     * The company associated with the user.
     * This variable is used to display the user's company in the application.
     */
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Company company;

    @Getter
    @Setter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_warehouse_association",
        joinColumns = @JoinColumn(name = "user_uuid"),
        inverseJoinColumns = @JoinColumn(name = "warehouse_uuid")
    )
    private Set<Warehouse> warehouse;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<InventoryTransaction> inventoryTransactions;

    @Getter
    @Setter
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Product> createdProducts;

    @Getter
    @Setter
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Product> updatedProducts;
}
