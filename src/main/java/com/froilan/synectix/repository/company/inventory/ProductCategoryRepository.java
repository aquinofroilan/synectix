package com.froilan.synectix.repository.company.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.froilan.synectix.model.inventory.ProductCategory;

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
}
