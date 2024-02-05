package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.repository.CarRepository;

import java.util.List;

public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(Cars car) {
        carRepository.addCar(car);
    }

    public List<Cars> findAll() {
        return carRepository.findAll();
    }

    public void deleteCar(Cars car) {
        carRepository.deleteCar(car);
    }

    public List<CarBrand> findAllBrands() {
        return carRepository.findAllBrands();
    }
}
