package com.froilan.synectix.model.inventory;

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

import java.util.UUID;

@Entity()
@Table(name = "warehouse", uniqueConstraints = @UniqueConstraint(columnNames = {"warehouse_uuid"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "warehouse_uuid", updatable = false, nullable = false)
    private UUID warehouseUuid;

    @Getter
    @Setter
    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company_uuid", referencedColumnName = "company_uuid", nullable = false)
    private Company company;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "warehouse_name", length = 100, columnDefinition = "VARCHAR(100)")
    private String warehouseName;

    @Getter
    @Setter
    @Column(nullable = false, name = "location", columnDefinition = "VARCHAR(255)")
    private String location;

    @Getter
    @Setter
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isActive;

}
