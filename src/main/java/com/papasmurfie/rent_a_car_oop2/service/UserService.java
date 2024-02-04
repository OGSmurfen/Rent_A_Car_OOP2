package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> findAll() {
        return userRepository.findAllOperators();
    }

    public void deleteOperator(Users operator) {
        userRepository.deleteOperator(operator);
    }

    public void addOperator(Users operator) {
        userRepository.save(operator);
    }
}
