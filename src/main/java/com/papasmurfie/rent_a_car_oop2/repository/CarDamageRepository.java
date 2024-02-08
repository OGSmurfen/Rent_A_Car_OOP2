package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cardamageprices;

import java.util.List;

public interface CarDamageRepository {
    List<Cardamageprices> findCarDamageByCarClass(CarClass carClass);
}
