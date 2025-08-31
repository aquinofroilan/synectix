package com.froilan.synectix.repository.user;

import com.froilan.synectix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link User} entities.
 *
 * <p>
 * This interface extends {@link JpaRepository}, providing built-in CRUD operations.
 * It also includes custom query methods for domain-specific user lookups such as
 * by email, phone number, and associations like company UUID.
 * </p>
 *
 * <p>
 * All methods leverage Spring Data JPA's method name parsing and custom query
 * annotations to facilitate expressive data retrieval.
 * </p>
 *
 * @author Froilan Aquino
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieves a user associated with a company, identified by the company's UUID.
     * <p>
     * This method leverages property path traversal (`company.uuid`) to perform the lookup.
     * </p>
     *
     * @param uuid the UUID of the associated company
     * @return an {@link Optional} containing the user if found, otherwise empty
     */
    Optional<User> findByCompany_Uuid(UUID uuid);

    /**
     * Searches users based on a general search term that may partially match
     * first name, last name, email, or phone number fields.
     * <p>
     * This query is case-insensitive for names and email, but case-sensitive for phone numbers.
     * It uses JPQL to concatenate wildcards for partial matches.
     * </p>
     *
     * @param searchTerm the term to search for
     * @return a list of users that match the search criteria
     */
    @Query("""
        SELECT u FROM User u WHERE
        LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR
        LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR
        LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR
        u.phoneNumber LIKE CONCAT('%', :searchTerm, '%')
    """)
    List<User> searchByTerm(@Param("searchTerm") String searchTerm);

    /**
     * Retrieves a user by their unique email address.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the user if found, otherwise empty
     */
    Optional<User> findByEmail(String email);

    /**
     * Retrieves a user by their unique username.
     *
     * @param username the username to search for
     * @return an {@link Optional} containing the user if found, otherwise empty
     */
    Optional<User> findByUsername(String username);

    /**
     * Retrieves a user by their unique phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return an {@link Optional} containing the user if found, otherwise empty
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

    /**
     * Checks for the existence of a user with the specified username.
     *
     * @param username the username to check
     * @return {@code true} if a user exists with the given username, otherwise {@code false}
     */
    boolean existsByUsername(String username);

    /**
     * Checks for the existence of a user with the specified phone number.
     *
     * @param phoneNumber the phone number to check
     * @return {@code true} if a user exists with the given phone number, otherwise {@code false}
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Checks for the existence of a user with the specified email address.
     *
     * @param email the email address to check
     * @return {@code true} if a user exists with the given email, otherwise {@code false}
     */
    boolean existsByEmail(String email);

}
