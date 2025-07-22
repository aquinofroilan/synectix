package com.froilan.synectix.model;

import com.froilan.synectix.model.lookup.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "company_id", "role_id",
        "phone_number" }))
public class UserCompanyRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The unique identifier for the user-company-role association.
     * This variable is used to display the association's ID in the application.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company companyId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role roleId;

}
