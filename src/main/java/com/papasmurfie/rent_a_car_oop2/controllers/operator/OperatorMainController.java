package com.papasmurfie.rent_a_car_oop2.controllers.operator;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class OperatorMainController {
    @FXML
    private TabPane operatorTabPane;
    @FXML
    private Tab operatorHomeTab;
    @FXML
    private Tab allRentsTab;

    public void initialize(){
        operatorTabPane.getSelectionModel().select(operatorHomeTab);
    }
}
