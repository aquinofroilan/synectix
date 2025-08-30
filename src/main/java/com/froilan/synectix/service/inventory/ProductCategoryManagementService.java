package com.froilan.synectix.service.inventory;

import com.froilan.synectix.exception.validation.NotFoundException;
import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.dto.request.inventory.ProductCategoryCreateBody;
import com.froilan.synectix.model.inventory.ProductCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    public Integer createProductCategory(ProductCategoryCreateBody newProductCategoryBody, String companyUuid)
            throws EntityNotFoundException, IllegalArgumentException, OptimisticLockingFailureException {
        Company companyReference = entityManager.getReference(Company.class, companyUuid);
        ProductCategory productCategory = ProductCategory.builder()
                .productCategoryCode(newProductCategoryBody.getProductCategoryCode())
                .productCategoryName(newProductCategoryBody.getProductCategoryName())
                .description(newProductCategoryBody.getDescription())
                .company(companyReference)
                .build();
        return productCategoryRepository.save(productCategory).getProductCategoryId();
    }

    @Transactional
    public boolean deleteProductCategory(Integer id, String companyUuid) throws NotFoundException {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("ProductCategory not found with ID: " + id));
        if (!productCategory.getCompany().getUuid().toString().equals(companyUuid)) {
            // TODO: Create and throw a custom exception for unauthorized access
            throw new NotFoundException("ProductCategory not found with ID: " + id);
        }
        productCategoryRepository.delete(productCategory);
        return true;
    }

    public List<ProductCategory>  getProductCategories(String companyUuid) {
        return productCategoryRepository.findAllByCompanyUuid(UUID.fromString(companyUuid));
    }

    public ProductCategory getProductCategory(Integer id) throws NotFoundException {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProductCategory not found with ID: " + id));
    }

    public Integer updateProductCategory(Integer valueOf, ProductCategoryCreateBody request, String companyUuid) {
        ProductCategory productCategory = productCategoryRepository.findById(valueOf)
                .orElseThrow(() -> new NotFoundException("ProductCategory not found with ID: " + valueOf));
        if (!productCategory.getCompany().getUuid().toString().equals(companyUuid)) {
            // TODO: Create and throw a custom exception for unauthorized access
            throw new NotFoundException("ProductCategory not found with ID: " + valueOf);
        }
        productCategory.setProductCategoryCode(request.getProductCategoryCode());
        productCategory.setProductCategoryName(request.getProductCategoryName());
        productCategory.setDescription(request.getDescription());
        return productCategoryRepository.save(productCategory).getProductCategoryId();
    }
}
