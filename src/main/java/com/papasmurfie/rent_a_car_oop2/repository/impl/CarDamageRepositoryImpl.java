package com.papasmurfie.rent_a_car_oop2.repository.impl;

import com.papasmurfie.rent_a_car_oop2.Transactions.HibernateTransactionManager;
import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cardamageprices;
import com.papasmurfie.rent_a_car_oop2.repository.CarDamageRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CarDamageRepositoryImpl implements CarDamageRepository {
    private final EntityManager entityManager;

    public CarDamageRepositoryImpl() {
        this.entityManager = HibernateTransactionManager.getEntityManager();
    }


    @Override
    public List<Cardamageprices> findCarDamageByCarClass(CarClass carClass) {
        return entityManager.createQuery("SELECT c FROM Cardamageprices c WHERE c.carClassId = :carClass", Cardamageprices.class)
                .setParameter("carClass", carClass.getId())
                .getResultList();
    }
}
