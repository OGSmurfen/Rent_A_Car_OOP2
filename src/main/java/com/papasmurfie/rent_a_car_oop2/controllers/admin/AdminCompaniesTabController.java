package com.papasmurfie.rent_a_car_oop2.controllers.admin;

import com.papasmurfie.rent_a_car_oop2.helpers.DatabaseConfig;
import com.papasmurfie.rent_a_car_oop2.models.CompanyDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminCompaniesTabController implements Initializable {
    //@FXMLs for Companies Tab
    @FXML
    private ImageView RefreshCompaniesImageView;
    @FXML
    private TableView<CompanyDataModel> CompaniesTableView;
    @FXML
    private TableColumn<CompanyDataModel, Integer> CompanyIdColumn;
    @FXML
    private TableColumn<CompanyDataModel, String> CompanyNameColumn;
    @FXML
    private TextField InsertCompanyIdField;
    @FXML
    private TextField InsertCompanyNameField;
    @FXML
    private Button AddCompanyButton;
    @FXML
    private Button DeleteCompanyButton;
    @FXML
    private Button RefreshCompaniesButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupUI();
        loadDatabase();
    }

    private void setupUI() {
        File RefreshCompaniesImageFile = new File("Images/refreshIconNoBG.png");
        Image RefreshCompaniesImage = new Image(RefreshCompaniesImageFile.toURI().toString());
        RefreshCompaniesImageView.setImage(RefreshCompaniesImage);
    }

    private void loadDatabase() {
        //DB connection for the COMPANY TABLEVIEW
        try {
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCompany = DatabaseConfig.getConnection();
            System.out.println("Database connection for Company table successful!");
            Statement statementCompany = connectionCompany.createStatement();
            // Retrieve data from the database
            ResultSet CompanyResultSet = statementCompany.executeQuery("SELECT company_id, company_name FROM company");
            // Create an ObservableList to hold the data
            ObservableList<CompanyDataModel> companyDataList = FXCollections.observableArrayList();
            // Populate the ObservableList with data from the ResultSet
            while (CompanyResultSet.next()) {
                int companyId = CompanyResultSet.getInt("company_id");
                String companyName = CompanyResultSet.getString("company_name");

                companyDataList.add(new CompanyDataModel(companyId, companyName));
            }

            // Set the ObservableList as the data source for the TableView
            CompaniesTableView.setItems(companyDataList);

            // Map the columns to the properties in the DataModel class
            CompanyIdColumn.setCellValueFactory(new PropertyValueFactory<>("companyId"));
            CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

            // Close the database connection
            CompanyResultSet.close();
            statementCompany.close();
            connectionCompany.close();

        } catch (Exception exceptionE) {
            System.out.println(exceptionE);
        }
    }

    public void DeleteCompanyButtonOnAction(ActionEvent event){
        //Select a row from tableview
        CompanyDataModel selectedItem = CompaniesTableView.getSelectionModel().getSelectedItem();
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
                PreparedStatement statement = connection.prepareStatement("DELETE FROM company WHERE company_id = ?");
                statement.setInt(1, selectedItem.getCompanyId());
                statement.executeUpdate();

                // Delete the row from the TableView
                CompaniesTableView.getItems().remove(selectedItem);

                // Show a success message
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Row deleted successfully.", ButtonType.OK);
                success.showAndWait();

                // Close the database connection
                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
                //show error
                Alert error = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the row.", ButtonType.OK);
                error.showAndWait();
            }
        }
    }

    public void AddCompanyButtonOnAction(ActionEvent event){
        if(InsertCompanyIdField.getText().isEmpty() || InsertCompanyNameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try {
            Connection connectionCompany = DatabaseConfig.getConnection();
            System.out.println("Database connection for Company table successful!");
            Statement statementCompany = connectionCompany.createStatement();
            //Retrieve data from the insert text fields:
            String NewCompanyId = InsertCompanyIdField.getText();
            String NewCompanyName = InsertCompanyNameField.getText();
            // Retrieve data from the database
            ResultSet CompanyResultSet = statementCompany.executeQuery("INSERT INTO company (company_id, company_name) VALUES ("+NewCompanyId+", '"+NewCompanyName+"')");
            // Create an ObservableList to hold the data
            ObservableList<CompanyDataModel> companyDataList = FXCollections.observableArrayList();
            // Populate the ObservableList with data from the ResultSet
            while (CompanyResultSet.next()) {
                int companyId = CompanyResultSet.getInt("company_id");
                String companyName = CompanyResultSet.getString("company_name");

                companyDataList.add(new CompanyDataModel(companyId, companyName));
            }

            // Set the ObservableList as the data source for the TableView
            CompaniesTableView.setItems(companyDataList);

            // Map the columns to the properties in the DataModel class
            CompanyIdColumn.setCellValueFactory(new PropertyValueFactory<>("companyId"));
            CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

            // Close the database connection
            CompanyResultSet.close();
            statementCompany.close();
            connectionCompany.close();



        } catch (Exception exceptionE) {
            System.out.println(exceptionE);
        }
    }

    public void RefreshCompaniesButtonOnAction(ActionEvent event){
        try{
            System.out.println("Refreshing Companies...");
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCompany = DatabaseConfig.getConnection();
            System.out.println("Database connection for Company table successful!");
            Statement statementCompany = connectionCompany.createStatement();
            // Retrieve data from the database
            ResultSet CompanyResultSet = statementCompany.executeQuery("SELECT company_id, company_name FROM company");
            // Create an ObservableList to hold the data
            ObservableList<CompanyDataModel> companyDataList = FXCollections.observableArrayList();
            // Populate the ObservableList with data from the ResultSet
            while (CompanyResultSet.next()) {
                int companyId = CompanyResultSet.getInt("company_id");
                String companyName = CompanyResultSet.getString("company_name");

                companyDataList.add(new CompanyDataModel(companyId, companyName));
            }

            // Set the ObservableList as the data source for the TableView
            CompaniesTableView.setItems(companyDataList);

            // Map the columns to the properties in the DataModel class
            CompanyIdColumn.setCellValueFactory(new PropertyValueFactory<>("companyId"));
            CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

            // Close the database connection
            CompanyResultSet.close();
            statementCompany.close();
            connectionCompany.close();



        } catch (Exception exceptionE){System.out.println(exceptionE);
        }
    }
}
