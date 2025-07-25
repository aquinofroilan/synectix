package com.froilan.synectix.service.lookup;

import java.util.List;

import org.springframework.stereotype.Service;

import com.froilan.synectix.model.lookup.Country;
import com.froilan.synectix.model.lookup.OrganizationType;
import com.froilan.synectix.repository.CountryRepository;
import com.froilan.synectix.repository.OrganizationTypeRepository;

@Service
public class LookupService {
    private final CountryRepository countryRepository;
    private final OrganizationTypeRepository organizationTypeRepository;

    public LookupService(CountryRepository countryRepository,
            OrganizationTypeRepository organizationTypeRepository) {
        this.countryRepository = countryRepository;
        this.organizationTypeRepository = organizationTypeRepository;
    }

    /**
     * Retrieves all countries from the repository.
     *
     * @return a list of all countries
     */
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    /**
     * Retrieves all organization types from the repository.
     *
     * @return a list of all organization types
     */
    public List<OrganizationType> getAllOrganizationTypes() {
        return organizationTypeRepository.findAll();
    }
}
