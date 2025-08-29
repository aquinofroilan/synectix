package com.froilan.synectix.model.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "id"}) })
public class Role {
    /**
     * The unique identifier for the role.
     * This variable is used to display the role's ID in the application.
     */
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the role.
     * This variable is used to display the role's name in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    /**
     * The description of the role.
     * This variable is used to display the role's description in the application.
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private String description;
}
