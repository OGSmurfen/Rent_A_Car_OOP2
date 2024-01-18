package com.papasmurfie.rent_a_car_oop2.controllers;

import com.papasmurfie.rent_a_car_oop2.models.CompanyDataModel;
import com.papasmurfie.rent_a_car_oop2.models.OperatorDataModel;
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
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    //@FXMLs for admin home
    @FXML
    private Button OKbutton;
    @FXML
    private ImageView Neo;
    @FXML
    private ImageView Morpheus;
    //@FXMLs for edit operators tab
    @FXML
    private TableView<OperatorDataModel> tableView;
    @FXML
    private TableColumn<OperatorDataModel, Integer> operatorIdColumn;
    @FXML
    private TableColumn<OperatorDataModel, String> operatorNamesColumn;
    @FXML
    private TableColumn<OperatorDataModel, String> operatorJobColumn;
    @FXML
    private TextField InsertOpIdField;
    @FXML
    private TextField InsertOpNameField;
    @FXML
    private TextField InsertOpJobField;
    @FXML
    private Button RefreshOperatorsButton;
    @FXML
    private ImageView RefreshImageDisplay;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){/*
        File regFile = new File("Images/register.png");
        Image regImage = new Image(regFile.toURI().toString());
        registerImageView.setImage(regImage);
        registrationMessageLabel.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        emptyPasswordLabel.setVisible(false);
        comingSoonLabel.setVisible(false);*/
        File NeoFile = new File("Images/neo.png");
        Image NeoImage = new Image(NeoFile.toURI().toString());
        //image for operators
        File RefreshImageFile = new File("Images/refreshIconNoBG.png");
        Image RefreshImage = new Image(RefreshImageFile.toURI().toString());
        RefreshImageDisplay.setImage(RefreshImage);
        Neo.setImage(NeoImage);
        //image for companies
        File RefreshCompaniesImageFile = new File("Images/refreshIconNoBG.png");
        Image RefreshCompaniesImage = new Image(RefreshCompaniesImageFile.toURI().toString());
        RefreshCompaniesImageView.setImage(RefreshCompaniesImage);
        File MorpheusFile = new File("Images/morpheus.png");
        Image MorpheusImage = new Image(MorpheusFile.toURI().toString());
        Morpheus.setImage(MorpheusImage);

        //establish DB connection for population of the Operators TableView
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
            System.out.println("Database connection for Operators table successful!");
            Statement statement = connection.createStatement();
            // Retrieve data from the database
            ResultSet OperatorsResultSet = statement.executeQuery("SELECT operator_id, operator_names, operator_job FROM OPERATORS");

            // Create an ObservableList to hold the data
            ObservableList<OperatorDataModel> dataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (OperatorsResultSet.next()) {
                int operatorId = OperatorsResultSet.getInt("operator_id");
                String operatorNames = OperatorsResultSet.getString("operator_names");
                String operatorJob = OperatorsResultSet.getString("operator_job");

                dataList.add(new OperatorDataModel(operatorId, operatorNames, operatorJob));
            }

            // Set the ObservableList as the data source for the TableView
            tableView.setItems(dataList);

            // Map the columns to the properties in the DataModel class
            operatorIdColumn.setCellValueFactory(new PropertyValueFactory<>("operatorId"));
            operatorNamesColumn.setCellValueFactory(new PropertyValueFactory<>("operatorNames"));
            operatorJobColumn.setCellValueFactory(new PropertyValueFactory<>("operatorJob"));

            // Close the database connection
            OperatorsResultSet.close();
            statement.close();
            connection.close();



        }catch (Exception exception){System.out.println(exception);}
        //DB connection for the COMPANY TABLEVIEW
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCompany = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
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



        }catch (Exception exceptionE){System.out.println(exceptionE);}


    }
    //OkButton = Logout Button
    public void OKbuttonOnAction(ActionEvent event){
        Stage stage = (Stage) OKbutton.getScene().getWindow();
        stage.close();
    }

    public void DeleteOperatorButtonOnAction(ActionEvent event){
        //Select a row from tableview
        OperatorDataModel selectedItem = tableView.getSelectionModel().getSelectedItem();
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
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
                System.out.println("Connection success!");
                PreparedStatement statement = connection.prepareStatement("DELETE FROM operators WHERE operator_id = ?");
                statement.setInt(1, selectedItem.getOperatorId());
                statement.executeUpdate();

                // Delete the row from the TableView
                tableView.getItems().remove(selectedItem);

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
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
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

            }catch (SQLException e){
                e.printStackTrace();
                //show error
                Alert error = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the row.", ButtonType.OK);
                error.showAndWait();
            }
        }
    }

    public void AddOperatorButtonOnAction(ActionEvent event){
        //if Insert TextField(s) empty -> Alert
        if(InsertOpIdField.getText().isEmpty() || InsertOpNameField.getText().isEmpty() || InsertOpJobField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }


        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
            System.out.println("Connection established...");
            Statement statement = connection.createStatement();

            //getText from the Insert fields
            String NewOperatorId = InsertOpIdField.getText();
            String NewOperatorName = InsertOpNameField.getText();
            String NewOperatorJob = InsertOpJobField.getText();

            System.out.println("Inserting...");
            String sql = "INSERT INTO operators (operator_id, operator_names, operator_job) VALUES ('"+NewOperatorId+"', '"+NewOperatorName+"', '"+NewOperatorJob+"')";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Statement InsertStatement = connection.createStatement();
            // Retrieve data from the database
            ResultSet resultSet = InsertStatement.executeQuery("SELECT operator_id, operator_names, operator_job FROM OPERATORS");

            // Create an ObservableList to hold the data
            ObservableList<OperatorDataModel> dataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (resultSet.next()) {
                int operatorId = resultSet.getInt("operator_id");
                String operatorNames = resultSet.getString("operator_names");
                String operatorJob = resultSet.getString("operator_job");

                dataList.add(new OperatorDataModel(operatorId, operatorNames, operatorJob));
            }

            // Set the ObservableList as the data source for the TableView
            tableView.setItems(dataList);

            // Map the columns to the properties in the DataModel class
            operatorIdColumn.setCellValueFactory(new PropertyValueFactory<>("operatorId"));
            operatorNamesColumn.setCellValueFactory(new PropertyValueFactory<>("operatorNames"));
            operatorJobColumn.setCellValueFactory(new PropertyValueFactory<>("operatorJob"));

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();



        }catch (Exception exception){System.out.println(exception);}

    }

    public void AddCompanyButtonOnAction(ActionEvent event){
        if(InsertCompanyIdField.getText().isEmpty() || InsertCompanyNameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCompany = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
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



        }catch (Exception exceptionE){System.out.println(exceptionE);}

    }

    public void RefreshOperatorsButtonOnAction(ActionEvent event){
        //same code from the initialize component only println differs
        try{
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
            System.out.println("Refreshing...");
            Statement statement = connection.createStatement();
            // Retrieve data from the database
            ResultSet resultSet = statement.executeQuery("SELECT operator_id, operator_names, operator_job FROM OPERATORS");

            // Create an ObservableList to hold the data
            ObservableList<OperatorDataModel> dataList = FXCollections.observableArrayList();

            // Populate the ObservableList with data from the ResultSet
            while (resultSet.next()) {
                int operatorId = resultSet.getInt("operator_id");
                String operatorNames = resultSet.getString("operator_names");
                String operatorJob = resultSet.getString("operator_job");

                dataList.add(new OperatorDataModel(operatorId, operatorNames, operatorJob));
            }

            // Set the ObservableList as the data source for the TableView
            tableView.setItems(dataList);

            // Map the columns to the properties in the DataModel class
            operatorIdColumn.setCellValueFactory(new PropertyValueFactory<>("operatorId"));
            operatorNamesColumn.setCellValueFactory(new PropertyValueFactory<>("operatorNames"));
            operatorJobColumn.setCellValueFactory(new PropertyValueFactory<>("operatorJob"));

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();



        }catch (Exception exception){System.out.println(exception);}

    }

    public void RefreshCompaniesButtonOnAction(ActionEvent event){
        try{
            System.out.println("Refreshing Companies...");
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectionCompany = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOP2", "postgres", "1");
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



        }catch (Exception exceptionE){System.out.println(exceptionE);}
    }
}
