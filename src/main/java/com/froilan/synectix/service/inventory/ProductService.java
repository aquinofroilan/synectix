package com.froilan.synectix.service.inventory;

import com.froilan.synectix.exception.validation.NotFoundException;
import com.froilan.synectix.model.inventory.Product;
import com.froilan.synectix.repository.company.inventory.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    public void createProduct() {
        // Implementation for creating a product goes here
    }

    public Product getProduct(UUID productUuid) throws NotFoundException {
       return  productRepository.findById(productUuid)
                .orElseThrow(() -> new NotFoundException("Product not found with UUID: " + productUuid));
    }

    public void deleteProduct(UUID productUuid) {
        productRepository.deleteById(productUuid);
    }

    public void deleteProducts(UUID[] productUuids) {
        productRepository.deleteAllById(List.of(productUuids));
    }
}
