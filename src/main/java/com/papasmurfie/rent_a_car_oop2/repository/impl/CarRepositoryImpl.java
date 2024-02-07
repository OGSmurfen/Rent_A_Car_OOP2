package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.CarCategory;
import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    private final EntityManager entityManager;

    public CarRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }
    @Override
    public List<Cars> findAll() {
        return entityManager.createQuery("SELECT c FROM Cars c ORDER BY id ASC ", Cars.class).getResultList();
    }

    public List<CarBrand> findAllBrands() {
        return entityManager.createQuery("SELECT c FROM CarBrand c", CarBrand.class).getResultList();
    }
    public List<CarClass> findAllClasses(){
        return entityManager.createQuery("SELECT c FROM CarClass c", CarClass.class).getResultList();
    }
    public List<CarCategory> findAllCategories(){
        return entityManager.createQuery("SELECT c FROM CarCategory c", CarCategory.class).getResultList();
    }

    @Override
    public CarBrand findBrand(String brandName){
        try {
            TypedQuery<CarBrand> query = entityManager.createQuery(
                    "SELECT c FROM CarBrand c WHERE c.name = :brandName", CarBrand.class);
            query.setParameter("brandName", brandName);

            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
    }

    @Override
    public CarClass findCarClass(String carClass) {
        try {
            TypedQuery<CarClass> query = entityManager.createQuery(
                    "SELECT c FROM CarClass c WHERE c.name = :carClass", CarClass.class);
            query.setParameter("carClass", carClass);

            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
    }

    @Override
    public CarCategory findCarCategory(String carCategory) {
        try {
            TypedQuery<CarCategory> query = entityManager.createQuery(
                    "SELECT c FROM CarCategory c WHERE c.name = :carCategory", CarCategory.class);
            query.setParameter("carCategory", carCategory);

            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
    }

    @Override
    public List<Cars> findAvailableCars(boolean selected) {
        try {
            TypedQuery<Cars> query = entityManager.createQuery(
                    "SELECT c FROM Cars c WHERE c.isrented = :isrented", Cars.class);
            query.setParameter("isrented", selected);

            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
    }

    @Override
    public void addCar(Cars car) {
        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCar(Cars car) {
        try {
            entityManager.getTransaction().begin();

            // Check if the entity is managed, if not, merge it
            if (!entityManager.contains(car)) {
                car = entityManager.merge(car);
            }

            // Delete the entity
            entityManager.remove(car);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }


    @Override
    public Cars updateCar(Cars car) {
        Cars existingCar = findById(car.getId());
        if(existingCar != null){
            entityManager.getTransaction().begin();
            existingCar.setCarBrand(car.getCarBrand());
            existingCar.setModel(car.getModel());
            existingCar.setCarClass(car.getCarClass());
            existingCar.setCarCategory(car.getCarCategory());
            existingCar.setCharacteristics(car.getCharacteristics());
            existingCar.setSmoker(car.isSmoker());
            existingCar.setIsrented(car.isIsrented());

            existingCar = entityManager.merge(existingCar);
            entityManager.getTransaction().commit();
        }
        return existingCar;
    }

    @Override
    public Cars findById(int id) {
        try {
            TypedQuery<Cars> query = entityManager.createQuery(
                    "SELECT c FROM Cars c WHERE c.id = :id", Cars.class);
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found (optional)
            return null;
        }
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
