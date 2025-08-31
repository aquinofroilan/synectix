package com.froilan.synectix.model.inventory;

import com.froilan.synectix.model.Company;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

@Entity()
@Table(name = "product_category", uniqueConstraints = @UniqueConstraint(columnNames = { "product_category_id" }))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    /**
     * The ID of the product category.
     * This variable is used to display the product category's ID in the application.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_category_id", updatable = false, nullable = false)
    private Integer productCategoryId;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company_uuid", nullable = false)
    private Company company;

    @Getter
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Product> products;

    /**
     * The name of the product category.
     * This variable is used to display the product category's name in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "product_category_name", length = 50)
    private String productCategoryName;

    /**
     * The code of the product category.
     * This variable is used to display the product category's code in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "productCategoryCode", length = 50)
    private String productCategoryCode;

    /**
     * The description of the product category.
     * This variable is used to display the product category's description in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "description")
    private String description;

    /**
     * Indicates whether the product category is active.
     * This variable is used to display the active status of the product category in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "is_active")
    private boolean isActive;

    /**
     * Timestamp when the product category was created.
     * This variable is used to display the creation time of the product category in the application.
     */
    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    /**
     * Timestamp when the product category was last updated.
     * This variable is used to display the last update time of the product category in the application.
     */
    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

}
