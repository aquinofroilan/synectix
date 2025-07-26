package com.froilan.synectix.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user in the PostgreSQL database.
 * This class is used to store user information such as username, first name,
 * last name, email, role, and password.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email", "uuid",
        "phone_number" }))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * The UUID of the user.
     * This variable is used to display the user's UUID in the application.
     */
    @Id
    @Getter
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
    @Column(nullable = false, unique = true, name = "hashed_password", columnDefinition = "VARCHAR(255)")
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
    @Builder.Default
    @Column(nullable = false, name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    /**
     * Indicates whether the user is active.
     * This variable is used to display the user's active status in the
     * application.
     */
    @Getter
    @Setter
    @Builder.Default
    @Column(nullable = false, name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive = true;

    @Setter
    @Getter
    @Builder.Default
    @Column(name = "last_login", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastLogin = LocalDateTime.now();

    /**
     * The timestamp when the user was created.
     * This variable is used to display the user's creation time in the
     * application.
     */
    @Getter
    @Builder.Default
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @PastOrPresent(message = "Created at must be in the past or present")
    @NotNull(message = "Created at cannot be null")
    private final LocalDateTime createdAt = LocalDateTime.now();

    /**
     * The timestamp when the user was last updated.
     * This variable is used to display the user's last update time in the
     * application.
     */
    @Getter
    @Setter
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @PastOrPresent(message = "Updated at must be in the past or present")
    private LocalDateTime updatedAt;

    /**
     * The company associated with the user.
     * This variable is used to display the user's company in the application.
     */
    @Getter
    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Company company;

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
