package com.papasmurfie.rent_a_car_oop2.controllers.operator;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.service.CarService;

import java.util.List;

public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void addCar(Cars car) {
        carService.addCar(car);
    }

    public List<Cars> findAll() {
        return carService.findAll();
    }

    public void deleteCar(Cars car) {
        carService.deleteCar(car);
    }

    public List<CarBrand> findAllBrands() {
        return carService.findAllBrands();
    }
}
