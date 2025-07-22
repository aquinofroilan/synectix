package com.froilan.synectix.model;

import java.util.UUID;

import com.froilan.synectix.model.lookup.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "user_company_role", uniqueConstraints = @UniqueConstraint(columnNames = { "user_uuid", "company_id",
        "role_id" }))
public class UserCompanyRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    /**
     * The user assigned to a specific role within a company.
     */
    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false, foreignKey = @ForeignKey(name = "fk_user_company_role_user"))
    private User user;

    /**
     * The company in which the user holds a specific role.
     */
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_company_role_company"))
    private Company company;

    /**
     * The role the user holds in the company.
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_company_role_role"))
    private Role role;

}
