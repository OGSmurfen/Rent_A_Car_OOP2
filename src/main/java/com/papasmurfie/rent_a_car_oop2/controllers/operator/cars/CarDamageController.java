package com.papasmurfie.rent_a_car_oop2.controllers.operator.cars;

import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cardamageprices;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.repository.impl.RentsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.CarDamageService;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;

import java.time.LocalDate;
import java.util.List;

public class CarDamageController {
    private final CarDamageService carDamageService;
    private final RentsService rentsService = new RentsService(new RentsRepositoryImpl());

    public CarDamageController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    public double calculateDamagePrice(Cars rentedCar, LocalDate returnDate, boolean isScratched, boolean isDented) {
        int rentId = rentedCar.getRent_id();
        Rents rent = rentsService.findRentById(rentId);
        LocalDate rentedDate = rent.getDateRented().toLocalDate();
        int daysRented = returnDate.compareTo(rentedDate);
        double totalPrince = 0.0;
        List<Cardamageprices> pricesList = findCarDamageByCarClass(rentedCar.getCarClass());
        for (Cardamageprices price : pricesList) {
            if (isScratched && price.getDamageConditionId() == 1) {
                totalPrince += price.getPrice().doubleValue();
            }
            if (isDented && price.getDamageConditionId() == 2) {
                totalPrince += price.getPrice().doubleValue();
            }
        }
        totalPrince += rentedCar.getDailyPrice().doubleValue() * daysRented;
        return totalPrince;
    }

    public List<Cardamageprices> findCarDamageByCarClass(CarClass carClass) {
        return carDamageService.findCarDamageByCarClass(carClass);
    }
}
