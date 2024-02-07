package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Company {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "company_id")
    private int companyId;
    @Basic
    @Column(name = "company_name")
    private String companyName;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyId == company.companyId && Objects.equals(companyName, company.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName);
    }
}
