package com.froilan.synectix.repository;

import com.froilan.synectix.model.lookup.OrganizationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Integer> {

}
