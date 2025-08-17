package com.froilan.synectix.service.inventory;

import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.inventory.WarehouseCreateBody;
import com.froilan.synectix.model.inventory.Warehouse;
import com.froilan.synectix.repository.company.inventory.WarehouseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class WarehouseManagementService {
    @PersistenceContext
    private EntityManager entityManager;
    private final WarehouseRepository warehouseRepository;

    public WarehouseManagementService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public Warehouse createWarehouse(WarehouseCreateBody newWarehouse, String userUuid, String companyUuid) throws EntityNotFoundException, IllegalArgumentException, OptimisticLockingFailureException {
        User creatorReference = entityManager.getReference(User.class, userUuid);
        Company companyReference = entityManager.getReference(Company.class, companyUuid);
        Warehouse warehouse = Warehouse.builder()
                .warehouseName(newWarehouse.getWarehouseName())
                .addressLine1(newWarehouse.getAddressLine1())
                .addressLine2(newWarehouse.getAddressLine2())
                .city(newWarehouse.getCity())
                .stateProvince(newWarehouse.getStateProvince())
                .postalCode(newWarehouse.getPostalCode())
                .warehouseType(newWarehouse.getWarehouseType())
                .capacityLimit(newWarehouse.getCapacityLimit())
                .capacityUnit(newWarehouse.getCapacityUnit())
                .isActive(newWarehouse.isActive())
                .company(companyReference)
                .createdBy(creatorReference)
                .updatedBy(creatorReference)
                .build();
        return warehouseRepository.save(warehouse);
    }
}
