package com.papasmurfie.rent_a_car_oop2.controllers.admin.operators;

import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.repository.impl.UserRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminOperatorsTabFormController implements Initializable {

    @FXML
    private TableView<Users> tableView;
    @FXML
    private TableColumn<Users, Integer> operatorIdColumn;
    @FXML
    private TableColumn<Users, String> operatorNamesColumn;
    @FXML
    private TextField InsertOperatorNameField;
    @FXML
    public PasswordField InsertOperatorPassword;

    private final OperatorController operatorController = new OperatorController(new UserService(new UserRepositoryImpl()));
    private ObservableList<Users> operatorsDataList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDatabase();
    }

    private void loadDatabase() {
        List<Users> operatorsList = operatorController.showAllUsers();
        operatorsDataList = FXCollections.observableArrayList(operatorsList);

        tableView.setItems(operatorsDataList);
        operatorIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        operatorNamesColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    public void DeleteOperatorButtonOnAction(ActionEvent event) {
        Users selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected row(s)?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            operatorController.deleteOperator(selectedItem);
            operatorsDataList.remove(selectedItem);
        }
    }

    public void AddOperatorButtonOnAction() {
        //if Insert TextField(s) empty -> Alert
        if (InsertOperatorNameField.getText().isEmpty() || InsertOperatorPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Users operator = new Users(InsertOperatorNameField.getText(), InsertOperatorPassword.getText(), 2);
        operatorController.addOperator(operator);
        operatorsDataList.add(operator);
        InsertOperatorNameField.setText("");
        InsertOperatorPassword.setText("");
    }
}
