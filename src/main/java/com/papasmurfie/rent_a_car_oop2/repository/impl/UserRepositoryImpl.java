package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;


    public UserRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }

    @Override
    public Users save(Users user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Users findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}