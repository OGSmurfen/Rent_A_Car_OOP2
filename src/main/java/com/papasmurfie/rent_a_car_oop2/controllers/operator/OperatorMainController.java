package com.papasmurfie.rent_a_car_oop2.controllers.operator;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class OperatorMainController {
    public TabPane operatorTabPane;
    public Tab operatorHomeTab;

    public void initialize(){
        operatorTabPane.getSelectionModel().select(operatorHomeTab);
    }
}
