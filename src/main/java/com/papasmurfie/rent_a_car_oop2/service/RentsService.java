package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.repository.RentsRepository;

import java.util.Date;
import java.util.List;

public class RentsService {
    private RentsRepository rentsRepository;

    public RentsService(RentsRepository rentsRepository) {
        this.rentsRepository = rentsRepository;
    }

    public Rents addRent(Rents rent) {
        return rentsRepository.addNew(rent);
    }

    public Rents findById(Rents rent) {
        return rentsRepository.findById(rent);
    }

    public List<Rents> findAll() {
        return rentsRepository.findAll();
    }

    public void deleteRent(Rents rent) {
        rentsRepository.deleteRent(rent);
    }

    public Rents updateRent(Rents rent) {
        return rentsRepository.updateRent(rent);
    }

    public List<Rents> findInDateDiapazon(Date begin, Date end) {
        return rentsRepository.findInDateDiapazon(begin, end);
    }
}
