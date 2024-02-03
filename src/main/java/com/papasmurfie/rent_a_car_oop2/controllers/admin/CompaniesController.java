package com.papasmurfie.rent_a_car_oop2.controllers.admin;

import com.papasmurfie.rent_a_car_oop2.entity.Company;
import com.papasmurfie.rent_a_car_oop2.service.CompanyService;

import java.util.List;

public class CompaniesController {
    private final CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    List<Company> getCompanies() {
    return companyService.findAll();
    }

    void deleteCompany(Company company) {
        companyService.deleteCompany(company);
    }

    void addCompany(Company company) {
        companyService.addCompany(company);
    }
}
