package com.example.rent_a_car_oop2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
    @FXML
    private ImageView registerImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File regFile = new File("Images/register.png");
        Image regImage = new Image(regFile.toURI().toString());
        registerImageView.setImage(regImage);

    }

}
