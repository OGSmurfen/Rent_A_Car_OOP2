package com.papasmurfie.rent_a_car_oop2.models;


public class CompanyDataModel {
    private int companyId;
    private String companyName;

    public CompanyDataModel(int companyId, String companyName){
        this.companyId = companyId;
        this.companyName = companyName;
    }

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
}
