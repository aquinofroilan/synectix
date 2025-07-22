package com.froilan.synectix.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.checkerframework.common.value.qual.BoolVal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
     * The UUID of the user.
     * This variable is used to display the user's UUID in the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    /**
     * The username of the user.
     * This variable is used to display the user's username in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "username", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 50, message = "Username cannot exceed 50 characters")
    private String username;

    /**
     * The first name of the user.
     * This variable is used to display the user's first name in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "first_name", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    /**
     * The last name of the user.
     * This variable is used to display the user's last name in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "last_name", length = 50, columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    /**
     * The email of the user.
     * This variable is used to display the user's email in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "email", length = 100, columnDefinition = "VARCHAR(100)")
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The phone number of the user.
     * This variable is used to display the user's phone number in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "phone_number", length = 15, columnDefinition = "VARCHAR(15)")
    @NotBlank(message = "Phone number cannot be blank")
    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9]{1,15}$", message = "Phone number must be valid")
    private String phoneNumber;

    /**
     * The password of the user.
     * This variable is used to display the user's password in the application.
     */
    @Setter
    @Getter
    @Column(nullable = false, unique = true, name = "hashed_password", length = 255, columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String hashedPassword;

    /**
     * Indicates whether the user is deleted.
     * This variable is used to display the user's deletion status in the
     * application.
     */
    @Getter
    @Setter
    @BoolVal(value = false)
    @Column(nullable = false, name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    /**
     * Indicates whether the user is active.
     * This variable is used to display the user's active status in the
     * application.
     */
    @Setter
    @Getter
    @BoolVal(value = true)
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive = true;

    /**
     * The timestamp when the user was created.
     * This variable is used to display the user's creation time in the
     * application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotBlank(message = "Created at cannot be blank")
    @Size(max = 50, message = "Created at cannot exceed 50 characters")
    @PastOrPresent(message = "Created at must be in the past or present")
    @FutureOrPresent(message = "Created at must be in the past or present")
    @NotNull(message = "Created at cannot be null")
    private LocalDateTime createdAt;

    /**
     * The timestamp when the user was last updated.
     * This variable is used to display the user's last update time in the
     * application.
     */
    @Getter
    @Setter
    @Column(nullable = false, unique = true, name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "User{" +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
