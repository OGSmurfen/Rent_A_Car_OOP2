package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.CarCategory;
import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;

import java.util.List;

public interface CarRepository {
    List<Cars> findAll();
    void addCar(Cars car);
    void deleteCar(Cars car);
    Cars updateCar(Cars car);
    Cars findById(int id);
    List<Cars> findByBrand(String brand);
    List<Cars> findByModel(String model);
    List<Cars> findByYear(int year);
    List<CarBrand> findAllBrands();
    List<CarClass> findAllClasses();
    List<CarCategory> findAllCategories();
    CarBrand findBrand(String brandName);

    CarClass findCarClass(String carClass);

    CarCategory findCarCategory(String carCategory);
}
