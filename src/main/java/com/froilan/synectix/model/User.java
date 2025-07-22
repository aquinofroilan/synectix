package com.froilan.synectix.model;

import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.froilan.synectix.model.enums.Role;

/**
 * Represents a user in the PostgreSQL database.
 * This class is used to store user information such as username, first name,
 * last name, email, role, and password.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email", "uuid" }))
public class User {
    /**
     * The unique identifier for the user.
     * This variable is used to display the user's ID in the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The UUID of the user.
     * This variable is used to display the user's UUID in the application.
     */
    @UUID
    private String uuid;

    /**
     * The username of the user.
     * This variable is used to display the user's username in the application.
     */
    @Column(nullable = false, unique = true, name = "username", length = 50, columnDefinition = "VARCHAR(50)")
    private String username;

    /**
     * The first name of the user.
     * This variable is used to display the user's first name in the application.
     */
    @Column(nullable = false, unique = true, name = "first_name", length = 50, columnDefinition = "VARCHAR(50)")
    private String firstName;

    /**
     * The last name of the user.
     * This variable is used to display the user's last name in the application.
     */
    @Column(nullable = false, unique = true, name = "last_name", length = 50, columnDefinition = "VARCHAR(50)")
    private String lastName;

    /**
     * The email of the user.
     * This variable is used to display the user's email in the application.
     */
    @Column(nullable = false, unique = true, name = "email", length = 100, columnDefinition = "VARCHAR(100)")
    private String email;

    /**
     * The role of the user.
     * This variable is used to display the user's role in the application.
     */
    @Column(nullable = false, name = "role", length = 20, columnDefinition = "VARCHAR(20)")
    private Role role;

    /**
     * The password of the user.
     * This variable is used to display the user's password in the application.
     */
    @Column(nullable = false, unique = true, name = "password", length = 255, columnDefinition = "VARCHAR(255)")
    private String password;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
