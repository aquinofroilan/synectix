package com.froilan.synectix.model.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrganizationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "name", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Organization type name cannot be blank")
    @Size(max = 50, message = "Organization type name cannot exceed 50 characters")
    private String name;
}
