package com.froilan.synectix.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.froilan.synectix.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their phone number.
     *
     * @param phoneNumber the phone number of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

    /**
     * Checks if a user exists by their username.
     *
     * @param username the username of the user
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user exists by their phone number.
     *
     * @param phoneNumber the phone number of the user
     * @return true if a user with the given phone number exists, false otherwise
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email of the user
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
