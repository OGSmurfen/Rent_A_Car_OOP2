package com.papasmurfie.rent_a_car_oop2.service;

import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.repository.UserRepository;

public class AuthenticationService {

    private UserRepository userRepository;

    // Constructor or setter for injecting the UserRepository
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateUser(String username, String password) {
        // Retrieve the user by username from the repository
        Users user = userRepository.findByUsername(username);

        // Check if the user exists and the provided password matches
        return user != null && validatePassword(user, password);
    }

    public int getRole(String username) {
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getRoleId();
        } else {
            return 0;
        }
    }

    private boolean validatePassword(Users user, String password) {
        // Implement password validation logic, e.g., using hashing algorithms
        // For simplicity, we'll assume a direct password match in this example
        return user.getPassword().equals(password);
    }

    public boolean signUpUser(String username, String password, String email) {
        // Implementation for user signup
        // Create a new user, validate input, etc.
        // For simplicity, we assume the provided username is unique
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Save the new user
        userRepository.save(newUser);
        return true;  // SignUp successful
    }
}
