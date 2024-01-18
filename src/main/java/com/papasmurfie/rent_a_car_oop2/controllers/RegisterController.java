package com.papasmurfie.rent_a_car_oop2.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
    @FXML
    private ImageView registerImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label emptyPasswordLabel;
    @FXML
    private Label comingSoonLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File regFile = new File("Images/register.png");
        Image regImage = new Image(regFile.toURI().toString());
        registerImageView.setImage(regImage);
        registrationMessageLabel.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        emptyPasswordLabel.setVisible(false);
        comingSoonLabel.setVisible(false);
    }

    public void registerButtonOnAction(ActionEvent event){
        confirmPasswordLabel.setVisible(false);
        emptyPasswordLabel.setVisible(false);
        registrationMessageLabel.setVisible(false);
        if(!Objects.equals(setPasswordField.getText(), "") && !Objects.equals(confirmPasswordLabel.getText(), ""))
        //check if password fields are empty
        {
            if(setPasswordField.getText().equals(confirmPasswordField.getText()))
            //check if passwords match
            {
                System.out.println("txt:"+setPasswordField.getText()+".");
                confirmPasswordLabel.setVisible(false);
                emptyPasswordLabel.setVisible(false);
                registerUser();//if all is fine, then we register user...
                registrationMessageLabel.setVisible(true);

            }else {
                registrationMessageLabel.setVisible(false);
                emptyPasswordLabel.setVisible(false);
                confirmPasswordLabel.setVisible(true);
            }
        }else{
            emptyPasswordLabel.setVisible(true);
        }
    }

    public void registerUser(){
        comingSoonLabel.setVisible(true);
        //connecing with the DB and adding new row...
        /*
        try{
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","OGSMURFEN","1");
            String username = usernameTextField.getText();
            String pwrd = enterPasswordField.getText();
            String sql = "select * from LOGIN where USERNAME = '"+usernaam+"' and PASSWORDD = '"+ pwrd +"'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                loginMessageLabel.setText("Login Success!");
                loginMessageLabel.setVisible(true);
                setLoginParam((getLoginParam())+(Integer.parseInt(rs.getString("LOGINPARAM"))));
                //the LOGINPARAM column contains loginParam; if loginParam == 1 => admin; if == 2 => user; 0 = no login

            }else{
                System.out.println(usernaam +" "+ pwrd);
                loginMessageLabel.setText("Invalid username & password!");
                loginMessageLabel.setVisible(true);
            }

        }catch (Exception exception){System.out.println(exception);}*/

    }

    public void closeButonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
       // Platform.exit();
    }

}
