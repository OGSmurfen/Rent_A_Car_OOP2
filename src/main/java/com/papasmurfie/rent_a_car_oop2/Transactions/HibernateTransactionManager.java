package com.papasmurfie.rent_a_car_oop2.Transactions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateTransactionManager {
    private static final String PERSISTENCE_UNIT_NAME = "Rent_a_car_OOP2";
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private HibernateTransactionManager() {}
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            initializeEntityManager();
        }

        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }

        return entityManager;
    }
    private static void initializeEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }

        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}