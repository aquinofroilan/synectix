package com.froilan.synectix.repository.company.inventory;

import com.froilan.synectix.model.inventory.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * Finds a product category by its name.
     *
     * @param productCategoryName the name of the product category
     * @return the product category if found, or null if not found
     */
    ProductCategory findByProductCategoryName(String productCategoryName);

    /**
     * Checks if a product category exists by its name.
     *
     * @param productCategoryName the name of the product category
     * @return true if a product category with the given name exists, false otherwise
     */
    boolean existsByProductCategoryName(String productCategoryName);

    /**
     * Get all product categories associated with a specific company.
     * @param companyUuid the UUID of the company
     * @return a list of product categories associated with the company
     */
     List<ProductCategory> findAllByCompanyUuid(UUID companyUuid);
}
