package com.example.rent_a_car_oop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button OKbutton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){/*
        File regFile = new File("Images/register.png");
        Image regImage = new Image(regFile.toURI().toString());
        registerImageView.setImage(regImage);
        registrationMessageLabel.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        emptyPasswordLabel.setVisible(false);
        comingSoonLabel.setVisible(false);*/
    }
    public void OKbuttonOnAction(ActionEvent event){
        Stage stage = (Stage) OKbutton.getScene().getWindow();
        stage.close();
    }

}
