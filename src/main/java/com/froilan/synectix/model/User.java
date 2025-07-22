package com.froilan.synectix.model;

import java.time.LocalDateTime;

import org.checkerframework.common.value.qual.BoolVal;
import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.froilan.synectix.model.enums.Country;
import com.froilan.synectix.model.enums.OrganizationType;

/**
 * Represents a user in the PostgreSQL database.
 * This class is used to store user information such as username, first name,
 * last name, email, role, and password.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email", "uuid",
        "phone_number" }))
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
     * The phone number of the user.
     * This variable is used to display the user's phone number in the application.
     */
    @Column(nullable = false, unique = true, name = "phone_number", length = 15, columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    /**
     * The country of the user.
     * This variable is used to display the user's country in the application.
     */
    @Column(nullable = false, unique = true, name = "country", length = 10, columnDefinition = "VARCHAR(5)")
    private Country country;

    /**
     * The organization type of the user.
     * This variable is used to display the user's organization type in the
     * application.
     */
    @Column(nullable = false, name = "organization_type", length = 20, columnDefinition = "VARCHAR(20)")
    private OrganizationType organizationType;

    /**
     * The password of the user.
     * This variable is used to display the user's password in the application.
     */
    @Column(nullable = false, unique = true, name = "password", length = 255, columnDefinition = "VARCHAR(255)")
    private String password;

    @BoolVal(value = false)
    private boolean isDeleted = false;

    /**
     * The timestamp when the user was created.
     * This variable is used to display the user's creation time in the
     * application.
     */
    @Column(nullable = false, unique = true, name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /**
     * The timestamp when the user was last updated.
     * This variable is used to display the user's last update time in the
     * application.
     */
    @Column(nullable = false, unique = true, name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country=" + country +
                ", organizationType=" + organizationType +
                ", password='" + password + '\'' +
                '}';
    }
}
