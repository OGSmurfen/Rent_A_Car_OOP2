package com.example.rent_a_car_oop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    @FXML
    private Button OKbutton;
    @FXML
    private ImageView Neo;
    @FXML
    private ImageView Morpheus;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){/*
        File regFile = new File("Images/register.png");
        Image regImage = new Image(regFile.toURI().toString());
        registerImageView.setImage(regImage);
        registrationMessageLabel.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        emptyPasswordLabel.setVisible(false);
        comingSoonLabel.setVisible(false);*/
        File NeoFile = new File("Images/neo.png");
        Image NeoImage = new Image(NeoFile.toURI().toString());
        Neo.setImage(NeoImage);
        File MorpheusFile = new File("Images/morpheus.png");
        Image MorpheusImage = new Image(MorpheusFile.toURI().toString());
        Morpheus.setImage(MorpheusImage);
    }
    public void OKbuttonOnAction(ActionEvent event){
        Stage stage = (Stage) OKbutton.getScene().getWindow();
        stage.close();
    }

}
