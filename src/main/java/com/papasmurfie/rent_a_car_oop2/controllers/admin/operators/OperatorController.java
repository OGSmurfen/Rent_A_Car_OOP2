package com.papasmurfie.rent_a_car_oop2.controllers.admin.operators;


import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.service.UserService;

import java.util.List;

public class OperatorController {

    private UserService userService;

    public OperatorController(UserService userService) {
        this.userService = userService;
    }

    public List<Users> showAllUsers() {
        return userService.findAll();
    }

    public void deleteOperator(Users operator) {
        userService.deleteOperator(operator);
    }

    public void addOperator(Users operator) {
        userService.addOperator(operator);
    }
}
