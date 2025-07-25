package com.froilan.synectix.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.froilan.synectix.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    /**
     * Finds a company by its registration number.
     *
     * @param registrationNumber the registration number of the company
     * @return an Optional containing the company if found, or empty if not found
     */
    Optional<Company> findByRegistrationNumber(String registrationNumber);

    /**
     * Checks if a company exists by its tax number.
     *
     * @param taxNumber the tax number of the company
     * @return true if a company with the given tax number exists, false otherwise
     */
    boolean existsByTaxNumber(String taxNumber);

}
