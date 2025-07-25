package com.froilan.synectix.model;

import java.util.UUID;

import com.froilan.synectix.model.lookup.Country;
import com.froilan.synectix.model.lookup.OrganizationType;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = { "uuid", "registration_number",
        "tax_number" }))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    /**
     * The UUID of the company.
     * This variable is used to display the company's UUID in the application.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    /**
     * The name of the company.
     * This variable is used to display the company's name in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "name", length = 100, columnDefinition = "VARCHAR(100)")
    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String name;

    /**
     * The registration number of the company.
     * This variable is used to display the company's registration number in the
     * application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "registration_number", length = 100, columnDefinition = "VARCHAR(100)")
    @NotBlank(message = "Registration number cannot be blank")
    @Size(max = 100, message = "Registration number cannot exceed 100 characters")
    private String registrationNumber;

    /**
     * The tax number of the company.
     * This variable is used to display the company's tax number in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "tax_number", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Tax number cannot be blank")
    @Size(max = 50, message = "Tax number cannot exceed 50 characters")
    private String taxNumber;

    /**
     * The country of the company.
     * This variable is used to display the company's country in the application.
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    /**
     * The organization type of the company.
     * This variable is used to display the company's organization type in the
     * application.
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "organization_type_id", nullable = false)
    private OrganizationType organizationType;
}
