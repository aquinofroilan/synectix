package com.froilan.synectix.service.inventory;

import com.froilan.synectix.exception.validation.NotFoundException;
import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.dto.request.inventory.ProductCategoryCreateBody;
import com.froilan.synectix.model.inventory.ProductCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.froilan.synectix.repository.company.inventory.ProductCategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCategoryManagementService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryManagementService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Transactional
    public void createProductCategory(ProductCategoryCreateBody newProductCategoryBody, String companyUuid)
            throws EntityNotFoundException, IllegalArgumentException, OptimisticLockingFailureException {
        Company companyReference = entityManager.getReference(Company.class, companyUuid);
        ProductCategory productCategory = ProductCategory.builder()
                .productCategoryCode(newProductCategoryBody.getProductCategoryCode())
                .productCategoryName(newProductCategoryBody.getProductCategoryName())
                .description(newProductCategoryBody.getDescription())
                .company(companyReference)
                .build();
        productCategoryRepository.save(productCategory);
    }

    @Transactional
    public void deleteProductCategory(Integer id) throws NotFoundException {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("ProductCategory not found with ID: " + id));
        productCategoryRepository.delete(productCategory);
    }

    public List<ProductCategory>  getProductCategories(String companyUuid) {
        // This method can be implemented to retrieve all product categories associated with a company.
        // For now, it is left empty as per the original code structure
        return productCategoryRepository.findAllByCompanyUuid(UUID.fromString(companyUuid));
    }

    public ProductCategory getProductCategory(Integer id) throws NotFoundException {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProductCategory not found with ID: " + id));
    }
}
