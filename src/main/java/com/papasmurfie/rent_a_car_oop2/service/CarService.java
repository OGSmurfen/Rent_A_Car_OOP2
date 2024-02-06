package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.CarCategory;
import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
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
    public CarBrand findBrand(String brandName){
        return carRepository.findBrand(brandName);
    }
    public List<CarClass> findAllClasses(){
        return carRepository.findAllClasses();
    }
    public List<CarCategory> findAllCategories(){
        return carRepository.findAllCategories();
    }

    public CarClass findCarClass(String carClass) {
        return carRepository.findCarClass(carClass);
    }

    public CarCategory findCarCategory(String carCategory) {
        return carRepository.findCarCategory(carCategory);
    }


    public Cars updateCar(Cars selectedCar) {
        return carRepository.updateCar(selectedCar);
    }

    public List<Cars> findAvailableCars(boolean selected) {
        return carRepository.findAvailableCars(selected);
    }
}
