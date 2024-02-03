package com.papasmurfie.rent_a_car_oop2.controllers.login;

import com.papasmurfie.rent_a_car_oop2.controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button cancelButton;

    private final AuthenticationController authenticationController;
    private final MainController mainController;

    // Event handler for login button click
    @FXML
    public void loginButtonOnAction() {
        // Get username and password from the UI components
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();

        // Call the AuthenticationController to handle the login request
        boolean isAuthenticated = authenticationController.authenticateUser(username, password);
        int roleId = authenticationController.getRole(username);
        // Handle the result of login
        if (isAuthenticated) {
            // Perform actions for successful login
            loginMessageLabel.setText("Login successful");  // Update a text element with a success message
            mainController.showLoggedInView(roleId);  // Change to another view on successful login
            loginMessageLabel.setVisible(true);
        } else {
            // Handle unsuccessful login (e.g., show an error message)
            loginMessageLabel.setText("Login failed");
            loginMessageLabel.setVisible(true);
        }
    }

    @FXML
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public LoginFormController(AuthenticationController authenticationController, MainController mainController) {
        this.authenticationController = authenticationController;
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupUI();
    }

    public void setupUI() {
        usernameTextField.setText("");
        enterPasswordField.setText("");
        File brandingFile = new File("Images/login_rentacar_2.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Images/logn_lok-removebg-preview.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        loginMessageLabel.setVisible(false);
    }
}