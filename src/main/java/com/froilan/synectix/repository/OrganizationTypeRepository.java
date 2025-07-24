package com.froilan.synectix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.froilan.synectix.model.lookup.OrganizationType;

@Repository
public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Integer> {

}
