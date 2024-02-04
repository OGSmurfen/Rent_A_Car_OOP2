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

public class MainController {
    private final Stage primaryStage;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginView() {
        try {
            System.out.println(getClass().getResource("LoginView.fxml"));
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
            loader.setController(new LoginFormController(new AuthenticationController(new AuthenticationService(new UserRepositoryImpl())), this));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Should use logger
        }
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
            //тук промених да не се извиква новия прозорец намястото на стария. С цел да можем при LOGOUTG бутон в админ
            //да се врщаме в LOGIn, а не да ни изхвърля. Може да се измисли начин за скриване на LOGIn формата.. ако искаш...
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdminMain.fxml"));
            Stage adminStage = new Stage();
            Scene scene = new Scene(loader.load(), 520, 447 );
            adminStage.initStyle(StageStyle.UNDECORATED);
            adminStage.setScene(scene);
            adminStage.show();
//TODO: да се измисли начин  да се скрива LOGIN формата след извикване на АДМИН форм. След което обратно показване... или не ?
 //           Parent root = loader.load();
//            primaryStage.setScene(new Scene(root));
//            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Should use logger
        }
    }

    public void showOperatorView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperatorMain.fxml"));
            Stage operatorStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 520, 447);
            operatorStage.initStyle(StageStyle.UNDECORATED);
            operatorStage.setScene(scene);
            operatorStage.show();
        } catch (Exception e){
            e.printStackTrace(); // Should use logger
            e.getCause();
        }
    }
}

