package com.froilan.synectix.model.inventory;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.froilan.synectix.model.Company;

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

@Entity()
@Table(name = "product_category", uniqueConstraints = @UniqueConstraint(columnNames = { "uuid", "registration_number",
    "tax_number" }))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "product_category_id", updatable = false, nullable = false)
    private Integer productCategoryId;

    @Getter
    @Setter
    @OneToOne(mappedBy = "product_category", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company_uuid", referencedColumnName = "company_uuid", nullable = false)
    private Company company;

    @Getter
    @Setter
    @OneToOne(mappedBy = "product_category", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_parent_category_id", referencedColumnName = "product_category_id", nullable = true)
    private ProductCategory parentCategoryId;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "product_category_name", length = 50, columnDefinition = "VARCHAR" +
        "(50)")
    private String productCategoryName;

    @Getter
    @Setter
    @Column(nullable = false, name = "productCategoryCode", length = 50, columnDefinition = "VARCHAR(50)")
    private String productCategoryCode;

    @Getter
    @Setter
    @Column(nullable = false, name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant updatedAt;

}
