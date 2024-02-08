package com.papasmurfie.rent_a_car_oop2.controllers.admin;

import com.papasmurfie.rent_a_car_oop2.controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class AdminMainController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab homeTab;


    public void initialize() {
        // Select the initial tab (e.g., homeTab)
        tabPane.getSelectionModel().select(homeTab);
    }
}
