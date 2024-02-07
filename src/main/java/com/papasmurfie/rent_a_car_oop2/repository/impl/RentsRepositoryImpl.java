package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.repository.RentsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class RentsRepositoryImpl implements RentsRepository {

    private final EntityManager entityManager;


    public RentsRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }
    @Override
    public Rents addNew(Rents rent) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(rent);
        transaction.commit();
        return rent;
    }

    @Override
    public Rents findById(int id) {
        try {
            return entityManager.createQuery("SELECT r FROM Rents r WHERE r.rentId = :rentId", Rents.class)
                    .setParameter("rentId", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Rents> findAll() {
        return null;
    }

    @Override
    public void deleteRent(Rents rent) {

    }

    @Override
    public Rents updateRent(Rents rent) {
        Rents existingRents = findById(rent.getRentId());
        if(existingRents != null) {
            entityManager.getTransaction().begin();
            existingRents.setDateReturned(rent.getDateReturned());
            existingRents.setReturnDescriptionProtocol(rent.getReturnDescriptionProtocol());
            existingRents.setKilometresDriven(rent.getKilometresDriven());
            entityManager.merge(existingRents);
            entityManager.getTransaction().commit();
        }
        return null;
    }

    @Override
    public List<Rents> findInDateDiapazon(Date begin, Date end) {
        return null;
    }

    @Override
    public int findClientByRentId(int rentId) {
        try {
            return entityManager.createQuery("SELECT r.clientId FROM Rents r WHERE r.rentId = :rentId", Integer.class)
                    .setParameter("rentId", rentId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return -1;
        }
    }

    @Override
    public Rents findRentById(Integer rentId) {
        try {
            return entityManager.createQuery("SELECT r FROM Rents r WHERE r.rentId = :rentId", Rents.class)
                    .setParameter("rentId", rentId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
