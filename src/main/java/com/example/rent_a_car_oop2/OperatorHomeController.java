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

public class OperatorHomeController implements Initializable {
    @FXML
    private Button closeButton;
    @FXML
    private ImageView SmithImageView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Images/TV-Smith-icon.png");
        Image SmithImage = new Image(brandingFile.toURI().toString());
        SmithImageView.setImage(SmithImage);

        /*File lockFile = new File("Images/logn_lok-removebg-preview.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);*/

        //loginMessageLabel.setVisible(false);
    }
    public void CloseButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
