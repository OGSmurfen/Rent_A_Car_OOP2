package com.papasmurfie.rent_a_car_oop2.controllers.operator;

import com.papasmurfie.rent_a_car_oop2.controllers.MainController;
import com.papasmurfie.rent_a_car_oop2.controllers.operator.rents.RentController;
import com.papasmurfie.rent_a_car_oop2.entity.Rents;
import com.papasmurfie.rent_a_car_oop2.repository.impl.RentsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperatorHomeTabController {
    @FXML
    private ImageView SmithImageView;
    @FXML
    private Button closeButton;

    private final RentController rentController = new RentController(new RentsService(new RentsRepositoryImpl()));

    public void initialize(){
        File brandingFile = new File("Images/TV-Smith-icon.png");
        Image SmithImage = new Image(brandingFile.toURI().toString());
        SmithImageView.setImage(SmithImage);
        checkRents();
    }

    private void checkRents() {
        Date today = new Date();
        List<Rents> rentsList = rentController.findInDateDiapazon(today, today);
        List<Rents> listOfNotReturnedRents = new ArrayList<>();
        for (Rents rent : rentsList) {
            if (rent.getReturnDescriptionProtocol().isEmpty()) {
                listOfNotReturnedRents.add(rent);
            }
        }
        if (!listOfNotReturnedRents.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rent Alert");
            alert.setHeaderText("You have " + listOfNotReturnedRents.size() + " rents today.");
            alert.setContentText("Please check the rents and make sure everything is in order.");
            alert.showAndWait();
        }
    }

    @FXML
    private void CloseButtonOnAction(ActionEvent event) {
        MainController mc = MainController.getInstance();
        mc.showLoginViewAgain();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
