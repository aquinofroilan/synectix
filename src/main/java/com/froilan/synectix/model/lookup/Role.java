package com.froilan.synectix.model.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "role")
public class Role {
    /**
     * The unique identifier for the role.
     * This variable is used to display the role's ID in the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the role.
     * This variable is used to display the role's name in the application.
     */
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Role name cannot be blank")
    @Size(max = 50, message = "Role name cannot exceed 50 characters")
    private String name;

    /**
     * The description of the role.
     * This variable is used to display the role's description in the application.
     */
    @Column()
    @Size(max = 255, message = "Role description cannot exceed 255 characters")
    private String description;
}
