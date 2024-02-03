package com.papasmurfie.rent_a_car_oop2;

import com.papasmurfie.rent_a_car_oop2.controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
//
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainController mainController = new MainController(stage);
        mainController.showLoginView();
    }

    public static void main(String[] args) {
        launch();
    }
}
