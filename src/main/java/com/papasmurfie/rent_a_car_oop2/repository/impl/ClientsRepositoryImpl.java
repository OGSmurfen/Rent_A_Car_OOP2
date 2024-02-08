package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Company;
import com.papasmurfie.rent_a_car_oop2.repository.ClientsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ClientsRepositoryImpl implements ClientsRepository {
    private final EntityManager cEntityManager;

    public ClientsRepositoryImpl() {
        this.cEntityManager = HibernateTransactionManager.getEntityManager();
    }

    @Override
    public List<Clients> findAll() {
        return cEntityManager.createQuery("SELECT c FROM Clients c", Clients.class).getResultList();
    }

    @Override
    public Clients addClient(Clients client) {
        EntityTransaction transaction = cEntityManager.getTransaction();
        transaction.begin();
        cEntityManager.persist(client);
        transaction.commit();
        return null;
    }

    @Override
    public void deleteClient(Clients client) {
        try {
            cEntityManager.getTransaction().begin();
            if (!cEntityManager.contains(client)) {
                client = cEntityManager.merge(client);
            }
            cEntityManager.remove(client);

            cEntityManager.getTransaction().commit();
        } catch (Exception e) {
            cEntityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Clients findById(int id) {
        return cEntityManager.find(Clients.class, id);
    }
}
