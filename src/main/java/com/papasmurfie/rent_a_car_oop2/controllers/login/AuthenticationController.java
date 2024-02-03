package com.papasmurfie.rent_a_car_oop2.controllers.login;

import com.papasmurfie.rent_a_car_oop2.service.AuthenticationService;

public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean authenticateUser(String username, String password) {
        return authenticationService.authenticateUser(username, password);
    }

    public boolean signUpUser(String username, String password, String email) {
        return authenticationService.signUpUser(username, password, email);
    }

    public int getRole(String username) {
        return authenticationService.getRole(username);
    }
}
