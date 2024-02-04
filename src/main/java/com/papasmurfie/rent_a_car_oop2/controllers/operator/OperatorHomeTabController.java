package com.papasmurfie.rent_a_car_oop2.controllers.operator;

import com.papasmurfie.rent_a_car_oop2.controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class OperatorHomeTabController {
    public ImageView SmithImageView;
    public Button closeButton;

    public void initialize(){
        File brandingFile = new File("Images/TV-Smith-icon.png");
        Image SmithImage = new Image(brandingFile.toURI().toString());
        SmithImageView.setImage(SmithImage);

    }
    public void CloseButtonOnAction(ActionEvent event) {
        MainController mc = MainController.getInstance();
        mc.showLoginViewAgain();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
