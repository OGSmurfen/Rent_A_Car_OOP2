package com.papasmurfie.rent_a_car_oop2.controllers;

import com.papasmurfie.rent_a_car_oop2.helpers.DatabaseConfig;
import com.papasmurfie.rent_a_car_oop2.models.CarsDataModel;
import com.papasmurfie.rent_a_car_oop2.models.ClientDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperatorHomeController implements Initializable {
    //Home Tab:
    @FXML
    private Button closeButton;
    @FXML
    private ImageView SmithImageView;

    //Clients Tab:
    @FXML
    private TableView<ClientDataModel> ClientsTableView;
    @FXML
    private TableColumn<ClientDataModel, Integer> ClientIdColumn;
    @FXML
    private TableColumn<ClientDataModel, String> ClientNameColumn;
    @FXML
    private TextField InsertClientIdField;
    @FXML
    private TextField InsertClientNameField;
    @FXML
    private Button AddClientButton;
    @FXML
    private Button DeleteClientButton;
    @FXML
    private Button RefreshClientsButton;
    @FXML
    private ImageView RefreshClientsImageView;

    //Cars Tab:
    @FXML
    private ComboBox<String> SelectClassComboBox;
    @FXML
    private ComboBox<String> SelectCategoryComboBox;
    @FXML
    private TableView<CarsDataModel> CarsTableView;
    @FXML
    private TableColumn<CarsDataModel, Integer> CarIdColumn;
    @FXML
    private TableColumn<CarsDataModel, String> CarBrandColumn;
    @FXML
    private TableColumn<CarsDataModel, String> CarModelColumn;
    @FXML
    private TableColumn<CarsDataModel, String> CarClassColumn;
    @FXML
    private TableColumn<CarsDataModel, String> CarCategoryColumn;
    @FXML
    private TableColumn<CarsDataModel, String> CarCharacteristicsColumn;
    @FXML
    private TableColumn<CarsDataModel, Byte> CarPhotoColumn;
    @FXML
    private TableColumn<CarsDataModel, String> CarSmokerColumn;
    @FXML
    private Button AddCarButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> SelectClassList = FXCollections.observableArrayList("Luxury", "Family", "City");
        SelectClassComboBox.setItems(SelectClassList);
        ObservableList<String> SelectCategoryList = FXCollections.observableArrayList("Sedan", "SUV", "Combi");
        SelectCategoryComboBox.setItems(SelectCategoryList);


        File brandingFile = new File("Images/TV-Smith-icon.png");
        Image SmithImage = new Image(brandingFile.toURI().toString());
        SmithImageView.setImage(SmithImage);
        File refreshClientFile = new File("Images/refreshIconNoBG.png");
        Image refreshClImage = new Image(refreshClientFile.toURI().toString());
        RefreshClientsImageView.setImage(refreshClImage);
        /*File lockFile = new File("Images/logn_lok-removebg-preview.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);*/

        //loginMessageLabel.setVisible(false);

        //ClientsTable DB conn:
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DatabaseConfig.getConnection();
            System.out.println("Database connection for Clients table successful!");
            Statement statement = connection.createStatement();
            // Retrieve data from the database
            ResultSet ClientsResultSet = statement.executeQuery("SELECT client_id, client_name FROM clients");

            // Create an ObservableList to hold the data
            ObservableList<ClientDataModel> clientDataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (ClientsResultSet.next()) {
                int clientId = ClientsResultSet.getInt("client_id");
                String clientName = ClientsResultSet.getString("client_name");

                clientDataList.add(new ClientDataModel(clientId, clientName));
            }

            // Set the ObservableList as the data source for the TableView
            ClientsTableView.setItems(clientDataList);

            // Map the columns to the properties in the DataModel class
            ClientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
            ClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));

            // Close the database connection
            ClientsResultSet.close();
            statement.close();
            connection.close();
   }catch (Exception exception){System.out.println(exception);}

        //DB connection for Cars table:
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCars = DatabaseConfig.getConnection();
            System.out.println("Database connection for Cars table successful!");
            Statement statementCars = connectionCars.createStatement();
            // Retrieve data from the database
            ResultSet CarsResultSet = statementCars.executeQuery("SELECT * FROM cars");

            // Create an ObservableList to hold the data
            ObservableList<CarsDataModel> carDataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (CarsResultSet.next()) {
                int carId = CarsResultSet.getInt("car_id");
                String carBrand = CarsResultSet.getString("car_brand");
                String carModel = CarsResultSet.getString("car_model");
                String carClass = CarsResultSet.getString("car_class");
                String carCategory = CarsResultSet.getString("car_category");
                String carCharacteristics = CarsResultSet.getString("car_characteristics");
                byte carPhoto = CarsResultSet.getByte("car_photo");
                String carSmoker = CarsResultSet.getString("car_smoker");

                carDataList.add(new CarsDataModel(carId, carBrand, carModel, carClass, carCategory,
                        carCharacteristics,carPhoto,carSmoker));
            }

            // Set the ObservableList as the data source for the TableView
            CarsTableView.setItems(carDataList);

            // Map the columns to the properties in the DataModel class
            CarIdColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));
            CarBrandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
            CarModelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
            CarClassColumn.setCellValueFactory(new PropertyValueFactory<>("carClass"));
            CarCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("carCategory"));
            CarCharacteristicsColumn.setCellValueFactory(new PropertyValueFactory<>("carCharacteristics"));
            CarPhotoColumn.setCellValueFactory(new PropertyValueFactory<>("carPhoto"));
            CarSmokerColumn.setCellValueFactory(new PropertyValueFactory<>("carSmoker"));

            // Close the database connection
            CarsResultSet.close();
            statementCars.close();
            connectionCars.close();



        }catch (Exception exception){System.out.println(exception);}

    }
    public void CloseButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void AddClientButtonOnAction(ActionEvent event){
        if(InsertClientIdField.getText().isEmpty() || InsertClientNameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        try{
            String clientIdIns = InsertClientIdField.getText();
            String clientNameIns = InsertClientNameField.getText();
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DatabaseConfig.getConnection();
            System.out.println("Database connection for Clients table successful!");
            Statement statement = connection.createStatement();
            // Retrieve data from the database
            ResultSet ClientsResultSet = statement.executeQuery("INSERT INTO clients (client_id, client_name) VALUES ('"+clientIdIns+"', '"+clientNameIns+"')");

            // Create an ObservableList to hold the data
            ObservableList<ClientDataModel> clientDataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (ClientsResultSet.next()) {
                int clientId = ClientsResultSet.getInt("client_id");
                String clientName = ClientsResultSet.getString("client_name");

                clientDataList.add(new ClientDataModel(clientId, clientName));
            }

            // Set the ObservableList as the data source for the TableView
            ClientsTableView.setItems(clientDataList);

            // Map the columns to the properties in the DataModel class
            ClientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
            ClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));

            // Close the database connection
            ClientsResultSet.close();
            statement.close();
            connection.close();
        }catch (Exception exception){System.out.println(exception);}

    }
    public void DeleteClientButtonOnAction(ActionEvent event){
        //Select a row from tableview
        ClientDataModel selectedItem = ClientsTableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            //alert for no row selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected row(s)?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if(confirmation.getResult() == ButtonType.YES)
        {
            //if YES
            try {
                Connection connection = DatabaseConfig.getConnection();
                System.out.println("Connection success!");
                PreparedStatement statement = connection.prepareStatement("DELETE FROM clients WHERE client_id = ?");
                statement.setInt(1, selectedItem.getClientId());
                statement.executeUpdate();

                // Delete the row from the TableView
                ClientsTableView.getItems().remove(selectedItem);

                // Show a success message
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Row deleted successfully.", ButtonType.OK);
                success.showAndWait();

                // Close the database connection
                statement.close();
                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
                //show error
                Alert error = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the row.", ButtonType.OK);
                error.showAndWait();
            }
        }
    }
    public void RefreshClientsButtonOnAction(ActionEvent event){
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DatabaseConfig.getConnection();
            System.out.println("Database connection for Clients table successful!");
            Statement statement = connection.createStatement();
            // Retrieve data from the database
            ResultSet ClientsResultSet = statement.executeQuery("SELECT client_id, client_name FROM clients");

            // Create an ObservableList to hold the data
            ObservableList<ClientDataModel> clientDataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (ClientsResultSet.next()) {
                int clientId = ClientsResultSet.getInt("client_id");
                String clientName = ClientsResultSet.getString("client_name");

                clientDataList.add(new ClientDataModel(clientId, clientName));
            }

            // Set the ObservableList as the data source for the TableView
            ClientsTableView.setItems(clientDataList);

            // Map the columns to the properties in the DataModel class
            ClientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
            ClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));

            // Close the database connection
            ClientsResultSet.close();
            statement.close();
            connection.close();



        }catch (Exception exception){System.out.println(exception);}
    }

    public void AddCarButtonOnAction(ActionEvent event){
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCars = DatabaseConfig.getConnection();
            System.out.println("Database connection for Cars table successful!");
            Statement statementCarsTwo = connectionCars.createStatement();
            File carImage = new File("Images/opel_corsa_2023.png");
            String query = "INSERT INTO cars(car_photo) VALUES(?)";
            PreparedStatement pst = connectionCars.prepareStatement(query);

                 try (FileInputStream fin = new FileInputStream(carImage)) {

                pst.setBinaryStream(1, fin, (int) carImage.length());
                pst.executeUpdate();
                     } catch (IOException ex) {
                Logger.getLogger(OperatorHomeController.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
            }




        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(OperatorHomeController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
