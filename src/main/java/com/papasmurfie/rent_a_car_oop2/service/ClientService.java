package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Company;
import com.papasmurfie.rent_a_car_oop2.repository.ClientsRepository;
import com.papasmurfie.rent_a_car_oop2.repository.CompanyRepository;

import java.util.List;

public class ClientService {
    private ClientsRepository clientsRepository;

    public ClientService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Clients> findAll() {
        return clientsRepository.findAll();
    }

    public void deleteClient(Clients client) {
        clientsRepository.deleteClient(client);
    }

    public void addClient(Clients client) {
        clientsRepository.addClient(client);
    }
}
