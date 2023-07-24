package com.example.rent_a_car_oop2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

//import javax.swing.*;
import java.io.File;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static int loginParam;

    {
        setLoginParam(0);
    }

    public static void setLoginParam(int loginParam) {
        LoginController.loginParam = loginParam;
    }


    public int getLoginParam(){
        return loginParam;
    }

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button registerButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        usernameTextField.setText("");
        enterPasswordField.setText("");
        File brandingFile = new File("Images/login_rentacar_2.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Images/logn_lok-removebg-preview.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        loginMessageLabel.setVisible(false);

    }

    public void loginButtonOnAction(ActionEvent event){
        setLoginParam(0);
        //loginMessageLabel.setText("");
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
            //clear credentials if valid!!
            usernameTextField.setText("");
            enterPasswordField.setText("");
            // loginMessageLabel.setText("Let's go!");
            // loginMessageLabel.setVisible(true);
        }else{
            loginMessageLabel.setText("Enter Username & Password");
            loginMessageLabel.setVisible(true);
        }

    }
    public void registerButtonOnAction(ActionEvent event){
        loginMessageLabel.setText("REGISTER BRO!!!");
        loginMessageLabel.setVisible(true);
        Stage loginStage = (Stage) registerButton.getScene().getWindow();
        //loginStage.close();
        createAccountForm();
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(){
        try{
            //connection for oracle db:
            //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","OGSMURFEN","1");
            //new connection for posgresql:
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
            System.out.println("Database connection successful!");
            String usernaam = usernameTextField.getText();
            String pwrd = enterPasswordField.getText();
            String sql = "select * from LOGIN where USERNAME = '"+usernaam+"' and PASSWORD = '"+ pwrd +"'";
            String operatorSQL = "SELECT * FROM operators WHERE operator_names = '"+usernaam+"' AND operator_job = '"+ pwrd +"'";
           // String sql = "select * from login where USERNAME = '"+usernaam+"' and PASSWORD = '"+ pwrd +"'";
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psTwo = conn.prepareStatement(operatorSQL);
            ResultSet rs = ps.executeQuery();
            ResultSet rsTwo = psTwo.executeQuery();
            if(rsTwo.next()){//IF it is an operator
                loginMessageLabel.setText("Operator Login Success!");
                loginMessageLabel.setVisible(true);
                OperatorLoginForm();
            }
            if(rs.next()){//if LOGIN is from loginTable
                loginMessageLabel.setText("Login Success!");
                loginMessageLabel.setVisible(true);
                setLoginParam((getLoginParam())+(Integer.parseInt(rs.getString("LOGINPARAM"))));
                System.out.println(getLoginParam());
                //the LOGINPARAM column contains loginParam; if loginParam == 1 => admin; if == 2 => user; 0 = no login
                if(loginParam == 1){
                AdminHomeForm();
                }else if(loginParam == 2){
                    OperatorLoginForm();
                }
                //if login is successful, we go to homepage

            }else{
                System.out.println(usernaam +" "+ pwrd);
                loginMessageLabel.setText("Invalid username & password!");
                loginMessageLabel.setVisible(true);
            }

        }catch (Exception exception){System.out.println(exception);}
    }
    public void AdminHomeForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminHome.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 520, 447);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void OperatorLoginForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperatorHome.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 520, 447);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAccountForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 520, 447);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    /* original:
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}