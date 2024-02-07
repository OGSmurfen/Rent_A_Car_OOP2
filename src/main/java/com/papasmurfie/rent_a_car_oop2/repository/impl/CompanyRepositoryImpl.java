package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.Company;
import com.papasmurfie.rent_a_car_oop2.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private final EntityManager entityManager;

    public CompanyRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }

    @Override
    public List<Company> findAll() {
        return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
    }

    @Override
    public Company addCompany(Company company) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(company);
        transaction.commit();
        return null;
    }

    @Override
    public void deleteCompany(Company company) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(company);
        transaction.commit();
    }

    @Override
    public Company findById(int id) {
       return entityManager.find(Company.class, id);
    }
}
