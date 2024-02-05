package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;

import java.util.List;

public interface CarRepository {
    List<Cars> findAll();
    void addCar(Cars car);
    void deleteCar(Cars car);
    void updateCar(Cars car);
    Cars findById(int id);
    List<Cars> findByBrand(String brand);
    List<Cars> findByModel(String model);
    List<Cars> findByYear(int year);

    List<CarBrand> findAllBrands();
}
