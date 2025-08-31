package com.froilan.synectix.service.inventory;

import com.froilan.synectix.exception.validation.NotFoundException;
import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.inventory.ProductCreateBody;
import com.froilan.synectix.model.inventory.Product;
import com.froilan.synectix.model.inventory.ProductCategory;
import com.froilan.synectix.repository.company.inventory.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public String createProduct(ProductCreateBody newProduct, String userUuid, String companyUuid) throws IllegalArgumentException, OptimisticLockingFailureException {
        ProductCategory productCategory = entityManager.find(ProductCategory.class, newProduct.getProductCategoryId());
        User userReference = entityManager.getReference(User.class, UUID.fromString(userUuid));
        Company companyReference = entityManager.getReference(Company.class, UUID.fromString(companyUuid));
        if (productCategory == null || userReference == null || companyReference == null) throw new IllegalArgumentException("Invalid product category ID, user ID, or company ID");
        Product product = Product.builder()
                .productName(newProduct.getProductName())
                .productDescription(newProduct.getProductDescription())
                .sellingPrice(newProduct.getSellingPrice())
                .baseCost(newProduct.getBaseCost())
                .productCategory(productCategory)
                .sku(newProduct.getSku())
                .brand(newProduct.getBrand())
                .model(newProduct.getModel())
                .productType(newProduct.getProductType())
                .unitMeasure(newProduct.getUnitMeasure())
                .weight(newProduct.getWeight())
                .weightUnit(newProduct.getWeightUnit())
                .dimensionsLength(newProduct.getDimensionsLength())
                .dimensionsWidth(newProduct.getDimensionsWidth())
                .dimensionsHeight(newProduct.getDimensionsHeight())
                .dimensionUnit(newProduct.getDimensionUnit())
                .barcode(newProduct.getBarcode())
                .qrCode(newProduct.getQrCode())
                .minimumStockLevel(newProduct.getMinimumStockLevel())
                .reorderPoint(newProduct.getReorderPoint())
                .reorderQuantity(newProduct.getReorderQuantity())
                .isActive(newProduct.isActive())
                .isSerialized(newProduct.isSerialized())
                .isLotTracked(newProduct.isLotTracked())
                .expirationTracking(newProduct.isExpirationTracking())
                .createdBy(userReference)
                .updatedBy(userReference)
                .company(companyReference)
                .build();

        return productRepository.save(product).getProductUuid().toString();
    }

    public Product getProduct(UUID productUuid) throws NotFoundException {
       return productRepository.findById(productUuid)
                .orElseThrow(() -> new NotFoundException("Product not found with UUID: " + productUuid));
    }

    public void deleteProduct(UUID productUuid) {
        productRepository.deleteById(productUuid);
    }

    public void deleteProducts(UUID[] productUuids) {
        productRepository.deleteAllById(List.of(productUuids));
    }
}
