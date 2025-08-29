package com.froilan.synectix.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.froilan.synectix.model.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "organization_type", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "id" }))
public class OrganizationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "name", length = 50)
    @NotBlank(message = "Organization type name cannot be blank")
    @Size(max = 50, message = "Organization type name cannot exceed 50 characters")
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "organizationType")
    @JsonIgnore
    private List<Company> companies;

    public OrganizationType(Integer id, @NotBlank(message = "Organization type name cannot be blank") @Size(max = 50, message = "Organization type name cannot exceed 50 characters") String name) {
    }

}
