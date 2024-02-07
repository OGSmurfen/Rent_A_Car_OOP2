package com.papasmurfie.rent_a_car_oop2.controllers.operator.rents;

import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;

import java.util.Date;
import java.util.List;

public class RentController {
    private RentsService rentsService;

    public RentController(RentsService rentsService) {
        this.rentsService = rentsService;
    }

    public Rents addRent(Rents rent) {
        return rentsService.addRent(rent);
    }

    public Rents findById(Rents rent) {
        return rentsService.findById(rent);
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

    public List<Rents> findBy(String type, String value) {
        return rentsService.findBy(type, value);
    }
}
