package com.papasmurfie.rent_a_car_oop2.controllers.login;

import org.junit.jupiter.api.Test;
import com.papasmurfie.rent_a_car_oop2.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationController = new AuthenticationController(authenticationService);
    }

    @Test
    void testAuthenticateUser() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        when(authenticationService.authenticateUser(username, password)).thenReturn(true);

        // Act
        boolean result = authenticationController.authenticateUser(username, password);

        // Assert
        assertTrue(result);
    }

    @Test
    void testSignUpUser() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String email = "test@example.com";
        when(authenticationService.signUpUser(username, password, email)).thenReturn(true);

        // Act
        boolean result = authenticationController.signUpUser(username, password, email);

        // Assert
        assertTrue(result);
    }

    @Test
    void testGetRole() {
        // Arrange
        String username = "testUser";
        int expectedRole = 1;
        when(authenticationService.getRole(username)).thenReturn(expectedRole);

        // Act
        int result = authenticationController.getRole(username);

        // Assert
        assertEquals(expectedRole, result);
    }
}



