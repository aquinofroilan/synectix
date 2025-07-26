package com.froilan.synectix.model.lookup;

import java.util.List;

import com.froilan.synectix.model.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Table(name = "country", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
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
    @Column(nullable = false, unique = true, name = "code", length = 2, columnDefinition = "CHAR(2)")
    private String code;

    @OneToMany(mappedBy = "country")
    private List<Company> companies;
}
