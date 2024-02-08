package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cardamageprices;
import com.papasmurfie.rent_a_car_oop2.repository.CarDamageRepository;

import java.util.List;

public class CarDamageService {
    private final CarDamageRepository carDamageRepository;

    public CarDamageService(CarDamageRepository carDamageRepository) {
        this.carDamageRepository = carDamageRepository;
    }

    public List<Cardamageprices> findCarDamageByCarClass(CarClass carClass) {
        return carDamageRepository.findCarDamageByCarClass(carClass);
    }
}
