package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Rents;

import java.util.Date;
import java.util.List;

public interface RentsRepository {
    Rents addNew(Rents rent);

    Rents findById(int id);

    List<Rents> findAll();
    void deleteRent(Rents rent);
    Rents updateRent(Rents rent);
    List<Rents> findInDateDiapazon(Date begin, Date end);


    Clients findClientByRentId(int rentId);

    Rents findRentById(Integer rentId);

    List<Rents> findBy(String type, String value);

}
