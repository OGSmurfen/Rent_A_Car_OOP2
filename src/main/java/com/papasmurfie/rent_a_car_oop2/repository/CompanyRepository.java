package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.Company;

import java.util.List;

public interface CompanyRepository {
    List<Company> findAll();

    Company addCompany(Company company);
    void deleteCompany(Company company);

    Company findById(int id);
}
