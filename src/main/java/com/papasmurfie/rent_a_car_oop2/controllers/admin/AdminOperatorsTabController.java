package com.papasmurfie.rent_a_car_oop2.controllers.admin;

import com.papasmurfie.rent_a_car_oop2.helpers.DatabaseConfig;
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

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminOperatorsTabController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       setupUI();
       loadDatabase();
    }

    private void setupUI() {
        File RefreshImageFile = new File("Images/refreshIconNoBG.png");
        Image RefreshImage = new Image(RefreshImageFile.toURI().toString());
        RefreshImageDisplay.setImage(RefreshImage);
    }

    private void loadDatabase() {
        try {
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DatabaseConfig.getConnection();
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

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void DeleteOperatorButtonOnAction(ActionEvent event){
        //Select a row from tableview
        OperatorDataModel selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            //alert for no row selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected row(s)?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if(confirmation.getResult() == ButtonType.YES) {
            //if YES
            try {
                Connection connection = DatabaseConfig.getConnection();
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

            } catch (SQLException e) {
                e.printStackTrace();
                //show error
                Alert error = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the row.", ButtonType.OK);
                error.showAndWait();
            }
        }
    }

    public void AddOperatorButtonOnAction(ActionEvent event){
        //if Insert TextField(s) empty -> Alert
        if(InsertOpIdField.getText().isEmpty() || InsertOpNameField.getText().isEmpty() || InsertOpJobField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        try {
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DatabaseConfig.getConnection();
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



        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void RefreshOperatorsButtonOnAction(ActionEvent event){
        //same code from the initialize component only println differs
        try {
            //DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DatabaseConfig.getConnection();
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



        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
