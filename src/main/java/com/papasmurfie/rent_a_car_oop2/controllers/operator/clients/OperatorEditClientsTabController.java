package com.papasmurfie.rent_a_car_oop2.controllers.operator.clients;

import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.entity.Users;
import com.papasmurfie.rent_a_car_oop2.repository.impl.ClientsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OperatorEditClientsTabController implements Initializable {
    @FXML
    private TableView ClientsTableView;
    @FXML
    private TableColumn ClientIdColumn;
    @FXML
    private TableColumn ClientNameColumn;
    @FXML
    private TextField InsertClientNameField;
    @FXML
    private Button AddClientButton;
    @FXML
    private Button DeleteClientButton;

    private final ClientsController clientsController = new ClientsController(new ClientService(new ClientsRepositoryImpl()));
    private ObservableList<Clients> clientsDataList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDatabase();
    }
    private void loadDatabase() {
        List<Clients> clientsList = clientsController.showAllUsers();
        clientsDataList = FXCollections.observableArrayList(clientsList);

        ClientsTableView.setItems(clientsDataList);
        ClientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        ClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
    }

    public void AddClientButtonOnAction(ActionEvent event) {
        //if Insert TextField(s) empty -> Alert
        if (InsertClientNameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Clients client = new Clients(InsertClientNameField.getText());
        clientsController.addOperator(client);
        clientsDataList.add(client);
        InsertClientNameField.setText("");
    }

    public void DeleteClientButtonOnAction(ActionEvent event) {
        Clients selectedCLient = (Clients) ClientsTableView.getSelectionModel().getSelectedItem();
        if (selectedCLient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected row(s)?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            clientsController.deleteOperator(selectedCLient);
            clientsDataList.remove(selectedCLient);
        }


    }

}
