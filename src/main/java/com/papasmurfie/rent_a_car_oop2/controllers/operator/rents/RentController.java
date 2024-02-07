package com.papasmurfie.rent_a_car_oop2.controllers.operator.rents;

import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RentController {
    private RentsService rentsService;

    public RentController(RentsService rentsService) {
        this.rentsService = rentsService;
    }

    public List<Rents> findAll() {
        return rentsService.findAll();
    }

    public void deleteRent(Rents rent) {
        rentsService.deleteRent(rent);
    }

    public Rents updateRent(Rents rent) {
        return rentsService.updateRent(rent);
    }

    public List<Rents> findInDateDiapazon(Date begin, Date end) {
        return rentsService.findInDateDiapazon(begin, end);
    }


    public Clients findClientByRentId(int rentId) {
        return rentsService.findClientByRentId(rentId);
    }

    public int rentCar(Cars rentCar, Clients client, LocalDate rentalDate, String description) {
        Rents rent = new Rents(rentCar, client, description, rentalDate);
        return rentsService.addRent(rent).getRentId();
    }

    public void returnCar(Cars rentCar, LocalDate returnDate, String returnDescription, int kilometres) {
        // rentCar, client, rentalDate, description, Integer.parseInt(kilometresTextField.getText()
        Rents rent = rentsService.findRentById(rentCar.getRent_id());
        rent.setDateReturned(returnDate);
        rent.setReturnDescriptionProtocol(returnDescription);
        rent.setKilometresDriven(kilometres);
        rentsService.updateRent(rent);
    }

    public List<Rents> findBy(String type, String value) {
        return rentsService.findBy(type, value);

    }
}
