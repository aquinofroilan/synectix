package com.froilan.synectix.model.lookup;

import com.froilan.synectix.model.Company;
import com.froilan.synectix.model.inventory.Warehouse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "country", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Country {
    /**
     * The ID of the country.
     * This variable is used to uniquely identify the country in the application.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The name of the country.
     * This variable is used to display the country's name in the application.
     */
    @Getter
    @Column(nullable = false, unique = true, name = "name", length = 50)
    private String name;

    @Getter
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Company> companies;

    @Getter
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Warehouse> warehouses;
}
