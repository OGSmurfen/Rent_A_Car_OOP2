package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.repository.RentsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class RentsRepositoryImpl implements RentsRepository {
    private final EntityManager entityManager;

    private final StringBuilder defaultQuery = new StringBuilder("SELECT r FROM Rents r WHERE 1=1");
    private StringBuilder tempQuery;

    public RentsRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
        tempQuery = defaultQuery;
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
        return entityManager.createQuery(defaultQuery.toString(), Rents.class).getResultList();
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
        try {
            TypedQuery<Rents> query = entityManager.createQuery(
                    "SELECT r FROM Rents r " +
                            "WHERE r.dateRented BETWEEN :startDate AND :endDate", Rents.class);

            query.setParameter("startDate", begin);
            query.setParameter("endDate", end);

            List<Rents> resultList = query.getResultList();

            return resultList;
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
    }

    @Override
    public List<Rents> findBy(String type, String value) {
        List<Rents> returnVal = null;
        if(type.equals("Class")){
            returnVal = findByClass(value);
        }
        if(type.equals("Category")){
            returnVal = findByCategory(value);
        }
    return returnVal;
    }

    private List<Rents> findByCategory(String categoryName) {
        try {
            TypedQuery<Rents> query = entityManager.createQuery(
                    "SELECT r FROM Rents r " +
                            "JOIN r.car c " +
                            "JOIN c.carCategory cc " +
                            "WHERE cc.name = :val", Rents.class);
            query.setParameter("val", categoryName);

            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
    }

    private List<Rents> findByClass(String className) {
        try {
            TypedQuery<Rents> query = entityManager.createQuery(
                    "SELECT r FROM Rents r " +
                            "JOIN r.car c " +
                            "JOIN c.carClass cc " +
                            "WHERE cc.name = :val", Rents.class);
            query.setParameter("val", className);

            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
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
