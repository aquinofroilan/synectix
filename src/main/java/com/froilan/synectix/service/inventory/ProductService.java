package com.froilan.synectix.service.inventory;

import com.froilan.synectix.exception.validation.NotFoundException;
import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.inventory.ProductCreateBody;
import com.froilan.synectix.model.dto.response.product.ProductDetailsDTO;
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

    public ProductDetailsDTO getProduct(String productUuid) throws NotFoundException {
       Product product = productRepository.findById(UUID.fromString(productUuid))
               .orElseThrow(() -> new NotFoundException("Product not found with UUID: " + productUuid));
        return ProductDetailsDTO.builder()
        .productUuid(product.getProductUuid().toString())
                .productCategory(product.getProductCategory().toString())
                .sku(product.getSku())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .brand(product.getBrand())
                .model(product.getModel())
                .productType(product.getProductType().toString())
                .unitMeasure(product.getUnitMeasure().toString())
                .baseCost(product.getBaseCost())
                .sellingPrice(product.getSellingPrice())
                .weight(product.getWeight())
                .weightUnit(product.getWeightUnit().toString())
                .dimensionsLength(product.getDimensionsLength())
                .dimensionsWidth(product.getDimensionsWidth())
                .dimensionsHeight(product.getDimensionsHeight())
                .dimensionUnit(product.getDimensionUnit().toString())
                .barcode(product.getBarcode())
                .qrCode(product.getQrCode())
                .minimumStockLevel(product.getMinimumStockLevel())
                .reorderPoint(product.getReorderPoint())
                .reorderQuantity(product.getReorderQuantity())
                .isActive(product.getIsActive())
                .isSerialized(product.getIsSerialized())
                .isLotTracked(product.getIsLotTracked())
                .expirationTracking(product.getExpirationTracking())
                .createdByUuid(product.getCreatedBy().getUserUuid().toString())
                .updatedByUuid(product.getUpdatedBy().getUserUuid().toString())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    @Transactional
    public boolean deleteProduct(UUID productUuid) {
        Product product = productRepository.findById(productUuid)
                .orElseThrow(() -> new NotFoundException("Product not found with UUID: " + productUuid));
        productRepository.delete(product);
        return true;
    }

    @Transactional
    public boolean deleteProducts(UUID[] productUuids) {
        List<Product> products = productRepository.findAllById(List.of(productUuids));
        if (products.size() != productUuids.length) throw new NotFoundException("One or more products not found for the provided UUIDs");
        productRepository.deleteAll(products);
        return true;
    }
}
