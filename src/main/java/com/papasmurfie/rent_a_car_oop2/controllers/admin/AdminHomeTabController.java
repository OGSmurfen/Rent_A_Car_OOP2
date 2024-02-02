package com.papasmurfie.rent_a_car_oop2.controllers.admin;

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

public class AdminHomeTabController implements Initializable {
    @FXML
    private Button OKbutton;
    @FXML
    private ImageView Neo;
    @FXML
    private ImageView Morpheus;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // home tab images
        File NeoFile = new File("Images/neo.png");
        Image NeoImage = new Image(NeoFile.toURI().toString());
        Neo.setImage(NeoImage);
        File MorpheusFile = new File("Images/morpheus.png");
        Image MorpheusImage = new Image(MorpheusFile.toURI().toString());
        Morpheus.setImage(MorpheusImage);
    }

    //OkButton = Logout Button
    public void OKbuttonOnAction() {
        Stage stage = (Stage) OKbutton.getScene().getWindow();
        stage.close();
    }
}
