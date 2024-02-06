package com.papasmurfie.rent_a_car_oop2.controllers.operator.rent_cars;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.CarCategory;
import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
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
    public CarBrand findBrand(String brand){
        return carService.findBrand(brand);
    }
    public List<CarClass> findAllClasses() {
        return carService.findAllClasses();
    }
    public List<CarCategory> findAllCategories() {
        return carService.findAllCategories();
    }

    public CarClass findCarClass(String carClass) {
        return carService.findCarClass(carClass);
    }

    public CarCategory findCarCategory(String carCategory) {
        return carService.findCarCategory(carCategory);
    }


    public Cars updateCar(Cars selectedCar) {
        return carService.updateCar(selectedCar);
    }
}