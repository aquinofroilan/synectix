package com.froilan.synectix.model.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "organization_types", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "id" }))
public class OrganizationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "name", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Organization type name cannot be blank")
    @Size(max = 50, message = "Organization type name cannot exceed 50 characters")
    private String name;
}
