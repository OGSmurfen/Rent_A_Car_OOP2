package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.repository.CarRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    private final EntityManager entityManager;

    public CarRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }
    @Override
    public List<Cars> findAll() {
        return entityManager.createQuery("SELECT c FROM Cars c", Cars.class).getResultList();
    }

    public List<CarBrand> findAllBrands() {
        return entityManager.createQuery("SELECT c FROM CarBrand c", CarBrand.class).getResultList();
    }

    @Override
    public void addCar(Cars car) {
        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCar(Cars car) {
        entityManager.getTransaction().begin();
        entityManager.remove(car);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateCar(Cars car) {

    }

    @Override
    public Cars findById(int id) {
        return null;
    }

    @Override
    public List<Cars> findByBrand(String brand) {
        return null;
    }

    @Override
    public List<Cars> findByModel(String model) {
        return null;
    }

    @Override
    public List<Cars> findByYear(int year) {
        return null;
    }
}
