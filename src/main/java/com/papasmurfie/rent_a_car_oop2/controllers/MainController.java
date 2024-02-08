package com.papasmurfie.rent_a_car_oop2.controllers;

import com.papasmurfie.rent_a_car_oop2.Main;
import com.papasmurfie.rent_a_car_oop2.controllers.login.AuthenticationController;
import com.papasmurfie.rent_a_car_oop2.controllers.login.LoginFormController;
import com.papasmurfie.rent_a_car_oop2.repository.impl.UserRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.AuthenticationService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.logging.Logger;

public class MainController {
    private static MainController instance;
    private Stage primaryStage;
    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }
    private Logger logger = Logger.getLogger(MainController.class.getName());

    private MainController() {

    }
    public void setPrimaryStage(Stage primaryStage){
        if(this.primaryStage == null){
        this.primaryStage = primaryStage;
        } else {
            logger.warning("Primary stage already set");
        }
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
            loader.setController(new LoginFormController(new AuthenticationController(new AuthenticationService(new UserRepositoryImpl())), this));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }
    }
    public void hideLoginView(){
        primaryStage.hide();
    }
    public void showLoginViewAgain(){
        primaryStage.show();
    }

    public void showLoggedInView(int roleId) {
        switch (roleId) {
            case 1:
                showAdminView();
                break;
            case 2:
                showOperatorView();
                break;
        }
    }

    private void showAdminView() {
        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdminMain.fxml"));
            Stage adminStage = new Stage();
            Scene scene = new Scene(loader.load(), 520, 447 );
            adminStage.initStyle(StageStyle.UNDECORATED);
            adminStage.setScene(scene);
            adminStage.show();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }
    }

    public void showOperatorView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperatorMain.fxml"));
            Stage operatorStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            operatorStage.initStyle(StageStyle.UNDECORATED);
            operatorStage.setScene(scene);
            operatorStage.show();
        } catch (Exception e){
            logger.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }
    }
}

