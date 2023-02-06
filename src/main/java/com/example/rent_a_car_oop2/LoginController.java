package com.example.rent_a_car_oop2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

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

    private void setLoginParam(int i) {
    }
    private int getLoginParam(){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Images/login_rentacar_2.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Images/logn_lok-removebg-preview.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        loginMessageLabel.setVisible(false);
    }

    public void loginButtonOnAction(ActionEvent event){
        //loginMessageLabel.setText("");
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
           // loginMessageLabel.setText("Let's go!");
            //loginMessageLabel.setVisible(true);
        }else{
            loginMessageLabel.setText("Enter Username & Password");
            loginMessageLabel.setVisible(true);
        }

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","OGSMURFEN","1");
            String usernaam = usernameTextField.getText();
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

        }catch (Exception exception){System.out.println(exception);}
    }

    /* original:
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}