package com.froilan.synectix.service.inventory;

import com.froilan.synectix.repository.company.inventory.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
