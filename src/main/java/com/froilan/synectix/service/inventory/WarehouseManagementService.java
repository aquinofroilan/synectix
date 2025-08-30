package com.froilan.synectix.service.inventory;

import com.froilan.synectix.exception.validation.NotFoundException;
import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.User;
import com.froilan.synectix.model.dto.request.inventory.WarehouseCreateBody;
import com.froilan.synectix.model.dto.response.warehouse.WarehouseDetails;
import com.froilan.synectix.model.dto.response.warehouse.WarehouseDetailsDTO;
import com.froilan.synectix.model.inventory.Warehouse;
import com.froilan.synectix.model.lookup.Country;
import com.froilan.synectix.repository.company.inventory.WarehouseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.UUID;

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
    public Warehouse createWarehouse(WarehouseCreateBody newWarehouse, String userUuid, String companyUuid)
            throws EntityNotFoundException, IllegalArgumentException, OptimisticLockingFailureException {
        User creatorReference = entityManager.getReference(User.class, UUID.fromString(userUuid));
        Company companyReference = entityManager.getReference(Company.class, UUID.fromString(companyUuid));
        Country countryReference = entityManager.getReference(Country.class, newWarehouse.getCountryId());
        Warehouse warehouse = Warehouse.builder()
                .warehouseName(newWarehouse.getWarehouseName())
                .addressLine1(newWarehouse.getAddressLine1())
                .addressLine2(newWarehouse.getAddressLine2())
                .city(newWarehouse.getCity())
                .stateProvince(newWarehouse.getStateProvince())
                .postalCode(newWarehouse.getPostalCode())
                .warehouseType(newWarehouse.getWarehouseType())
                .warehouseCode(newWarehouse.getWarehouseCode())
                .description(newWarehouse.getDescription())
                .capacityLimit(newWarehouse.getCapacityLimit())
                .capacityUnit(newWarehouse.getCapacityUnit())
                .isActive(newWarehouse.isActive())
                .country(countryReference)
                .company(companyReference)
                .createdBy(creatorReference)
                .updatedBy(creatorReference)
                .build();
        return warehouseRepository.save(warehouse);
    }

    public WarehouseDetailsDTO getWarehouse(String uuid) throws EntityNotFoundException {
        Warehouse warehouse = warehouseRepository.findByWarehouseUuidWithRelations(UUID.fromString(uuid))
                .orElseThrow(() -> new NotFoundException("Warehouse not found with UUID: " + uuid));
        return WarehouseDetailsDTO.builder()
                .warehouseUuid(warehouse.getWarehouseUuid())
                .warehouseName(warehouse.getWarehouseName())
                .warehouseCode(warehouse.getWarehouseCode())
                .addressLine1(warehouse.getAddressLine1())
                .addressLine2(warehouse.getAddressLine2())
                .city(warehouse.getCity())
                .stateProvince(warehouse.getStateProvince())
                .postalCode(warehouse.getPostalCode())
                .warehouseType(warehouse.getWarehouseType())
                .capacityLimit(warehouse.getCapacityLimit())
                .capacityUnit(warehouse.getCapacityUnit())
                .isActive(warehouse.isActive())
                .companyUuid(warehouse.getCompany().getUuid())
                .companyName(warehouse.getCompany().getName())
                .countryId(warehouse.getCountry().getId())
                .countryName(warehouse.getCountry().getName())
                .createdByUserUuid(warehouse.getCreatedBy().getUserUuid())
                .createdByUsername(warehouse.getCreatedBy().getUsername())
                .updatedByUserUuid(warehouse.getUpdatedBy().getUserUuid())
                .updatedByUsername(warehouse.getUpdatedBy().getUsername())
                .build();
    }

    @Transactional
    public void deleteWarehouse(String uuid) throws EntityNotFoundException {
        Warehouse warehouse = warehouseRepository.findByWarehouseUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new NotFoundException("Warehouse not found with UUID: " + uuid));
        warehouseRepository.delete(warehouse);
    }

    @Transactional
    public void updateWarehouse(String uuid, WarehouseCreateBody updatedWarehouse, String userUuid, String companyUuid, String warehouseUuid)
            throws EntityNotFoundException, IllegalArgumentException, OptimisticLockingFailureException {
        Warehouse existingWarehouse = warehouseRepository.findByWarehouseUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new NotFoundException("Warehouse not found with UUID: " + uuid));
        if (!existingWarehouse.getCompany().getUuid().toString().equals(companyUuid)
                || !existingWarehouse.getWarehouseUuid().toString().equals(warehouseUuid)) {
            // TODO: Create custom exception
            throw new IllegalArgumentException("Company UUID or Warehouse UUID mismatch");
        }

        User creatorReference = entityManager.getReference(User.class, userUuid);
        existingWarehouse.setWarehouseName(updatedWarehouse.getWarehouseName());
        existingWarehouse.setAddressLine1(updatedWarehouse.getAddressLine1());
        existingWarehouse.setAddressLine2(updatedWarehouse.getAddressLine2());
        existingWarehouse.setCity(updatedWarehouse.getCity());
        existingWarehouse.setStateProvince(updatedWarehouse.getStateProvince());
        existingWarehouse.setPostalCode(updatedWarehouse.getPostalCode());
        existingWarehouse.setWarehouseType(updatedWarehouse.getWarehouseType());
        existingWarehouse.setCapacityLimit(updatedWarehouse.getCapacityLimit());
        existingWarehouse.setCapacityUnit(updatedWarehouse.getCapacityUnit());
        existingWarehouse.setActive(updatedWarehouse.isActive());
        existingWarehouse.setCreatedBy(creatorReference);
        existingWarehouse.setUpdatedBy(creatorReference);
        warehouseRepository.save(existingWarehouse);
    }
}
