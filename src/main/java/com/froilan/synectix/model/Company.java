package com.froilan.synectix.model;

import org.hibernate.validator.constraints.UUID;

import com.froilan.synectix.model.enums.Country;
import com.froilan.synectix.model.enums.OrganizationType;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Company {

    /**
     * The unique identifier for the company.
     * This variable is used to display the company's ID in the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The UUID of the company.
     * This variable is used to display the company's UUID in the application.
     */
    @UUID
    @Column(nullable = false, unique = true, name = "uuid", length = 36, columnDefinition = "VARCHAR(36)")
    private String uuid;

    /**
     * The name of the company.
     * This variable is used to display the company's name in the application.
     */
    @Column(nullable = false, unique = true, name = "name", length = 100, columnDefinition = "VARCHAR(100)")
    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String name;

    /**
     * The registration number of the company.
     * This variable is used to display the company's registration number in the
     * application.
     */
    @Column(nullable = false, unique = true, name = "registration_number", length = 100, columnDefinition = "VARCHAR(100)")
    @NotBlank(message = "Registration number cannot be blank")
    @Size(max = 100, message = "Registration number cannot exceed 100 characters")
    private String registrationNumber;

    /**
     * The tax number of the company.
     * This variable is used to display the company's tax number in the application.
     */
    @Column(nullable = false, unique = true, name = "tax_number", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Tax number cannot be blank")
    @Size(max = 50, message = "Tax number cannot exceed 50 characters")
    private String taxNumber;

    /**
     * The country of the company.
     * This variable is used to display the company's country in the application.
     */
    @Column(nullable = false, unique = true, name = "country", length = 10, columnDefinition = "VARCHAR(5)")
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 5, message = "Country cannot exceed 5 characters")
    private Country country;

    /**
     * The organization type of the company.
     * This variable is used to display the company's organization type in the
     * application.
     */
    @Column(nullable = false, name = "organization_type", length = 20, columnDefinition = "VARCHAR(20)")
    @NotBlank(message = "Organization type cannot be blank")
    @Size(max = 20, message = "Organization type cannot exceed 20 characters")
    private OrganizationType organizationType;
}
