package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.Users;

import java.util.List;

public interface UserRepository {
    Users save(Users user);
    Users findByUsername(String username);

    List<Users> findAllOperators();

    void deleteOperator(Users operator);
}
