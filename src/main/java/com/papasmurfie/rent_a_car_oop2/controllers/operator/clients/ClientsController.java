package com.papasmurfie.rent_a_car_oop2.controllers.operator.clients;

import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.service.ClientService;
import com.papasmurfie.rent_a_car_oop2.service.UserService;

import java.util.List;

public class ClientsController {
    private ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Clients> showAllUsers() {
        return clientService.findAll();
    }

    public void deleteOperator(Clients client) {
        clientService.deleteClient(client);
    }

    public void addOperator(Clients client) {
        clientService.addClient(client);
    }
}
