package com.papasmurfie.rent_a_car_oop2.repository;

import com.papasmurfie.rent_a_car_oop2.entity.Users;

public interface UserRepository {
    Users save(Users user);
    Users findByUsername(String username);
}
