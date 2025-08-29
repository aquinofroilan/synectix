package com.froilan.synectix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.froilan.synectix.model.lookup.OrganizationType;

import java.util.List;

@Repository
public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Integer> {

    @Query("SELECT new com.froilan.synectix.model.lookup.OrganizationType(ot.id, ot.name) FROM OrganizationType ot")
    List<OrganizationType> findAllOrganizationTypeWithoutCompanies();

}
