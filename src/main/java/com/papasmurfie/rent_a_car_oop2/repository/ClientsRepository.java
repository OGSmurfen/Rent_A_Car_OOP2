package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Company;

import java.util.List;

public interface ClientsRepository {
    List<Clients> findAll();

    Clients addClient(Clients client);
    void deleteClient(Clients client);

    Clients findById(int id);
}
