package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.Company;
import com.papasmurfie.rent_a_car_oop2.repository.CompanyRepository;

import java.util.List;

public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void deleteCompany(Company company) {
        companyRepository.deleteCompany(company);
    }

    public void addCompany(Company company) {
        companyRepository.addCompany(company);
    }
}
