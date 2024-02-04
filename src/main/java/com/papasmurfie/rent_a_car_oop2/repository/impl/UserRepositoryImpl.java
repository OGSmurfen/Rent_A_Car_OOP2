package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;


    public UserRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }


    @Override
    public Users save(Users user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
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

    @Override
    public List<Users> findAllOperators() {
        try {
            return entityManager.createQuery("SELECT u FROM Users u WHERE u.roleId = 2", Users.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteOperator(Users operator) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(operator);
        transaction.commit();
    }
}