package com.papasmurfie.rent_a_car_oop2.controllers.operator.cars;

import com.papasmurfie.rent_a_car_oop2.controllers.operator.clients.ClientsController;
import com.papasmurfie.rent_a_car_oop2.controllers.operator.rents.RentController;
import com.papasmurfie.rent_a_car_oop2.entity.CarCategory;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.entity.Clients;
import com.papasmurfie.rent_a_car_oop2.repository.impl.CarRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.repository.impl.ClientsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.repository.impl.RentsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.CarService;
import com.papasmurfie.rent_a_car_oop2.service.ClientService;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OperatorRentCarDialogFormController implements Initializable {
    @FXML
    private ComboBox<Clients> clientComboBox;
    @FXML
    private ImageView carImageView;
    @FXML
    private DatePicker rentalDatePicker;
    @FXML
    private TextArea rentalDescription;
    @FXML
    private Button rentButton;
    @FXML
    private Label carNameLabel;

    @FXML
    private TextField kilometresTextField;

    private final Cars rentCar;

    private final ClientsController clientsController = new ClientsController(new ClientService(new ClientsRepositoryImpl()));

    private final RentController rentController = new RentController(new RentsService(new RentsRepositoryImpl()));

    private final CarController carController = new CarController(new CarService(new CarRepositoryImpl()));

    private OperatorRentCarTabFormController operatorRentCarTabFormController;

    private OperatorRentCarDialogFormConfiguration operatorRentCarDialogFormConfiguration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateComboBox();
        setupUI();
    }

    public OperatorRentCarDialogFormController(Cars rentCar, OperatorRentCarTabFormController operatorRentCarTabFormController, OperatorRentCarDialogFormConfiguration operatorRentCarDialogFormConfiguration) {
        this.rentCar = rentCar;
        this.operatorRentCarTabFormController = operatorRentCarTabFormController;
        this.operatorRentCarDialogFormConfiguration = operatorRentCarDialogFormConfiguration;
    }

    private void setupUI() {
        switch (operatorRentCarDialogFormConfiguration) {
            case RENT:
                break;
            case RETURN:
                rentButton.setText("Return");
                clientComboBox.setDisable(true);
                // find client by rent id
                Clients client = rentController.findClientByRentId(rentCar.getRent_id());
                ObservableList<Clients> clientsDataList = FXCollections.observableArrayList(client);
                clientComboBox.setItems(clientsDataList);
                clientComboBox.getSelectionModel().selectFirst();
                kilometresTextField.setVisible(true);
                break;
        }

        setupCarInfo();
    }
    @FXML
    private void rentButtonClicked(ActionEvent actionEvent) {
        Clients client = clientComboBox.getValue();
        LocalDate rentalDate = rentalDatePicker.getValue();
        String description = rentalDescription.getText();
        int kilometres = 0;
        if (!kilometresTextField.getText().isEmpty()) {
            kilometres = Integer.parseInt(kilometresTextField.getText());
        }
        if (client == null || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } else {
           switch (operatorRentCarDialogFormConfiguration) {
                case RENT:
                    rentButtonAction(client, rentalDate, description);
                    break;
                case RETURN:
                    returnButtonClicked(rentalDate, description, kilometres);
                    break;
            }
            Stage stage = (Stage) rentButton.getScene().getWindow();
            stage.close();
            operatorRentCarTabFormController.populateTable();
        }
    }


    private void rentButtonAction(Clients client, LocalDate rentalDate, String description) {
        int rentId = rentController.rentCar(rentCar, client, rentalDate, description);
        rentCar.setIsrented(true);
        rentCar.setRent_id(rentId);
        carController.updateCar(rentCar);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("Car rented successfully");
        alert.showAndWait();
    }

    private void returnButtonClicked(LocalDate rentalDate, String description, int kilometres) {
        rentController.returnCar(rentCar, rentalDate, description, kilometres);
        rentCar.setIsrented(false);
        rentCar.setRent_id(null);
        carController.updateCar(rentCar);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("Car returned successfully");
        alert.showAndWait();
        Stage stage = (Stage) rentButton.getScene().getWindow();
        stage.close();
        operatorRentCarTabFormController.populateTable();
    }

    private void populateComboBox() {
        clientComboBox.getItems().addAll(clientsController.showAllUsers());
    }

    public void setupCarInfo() {
        carNameLabel.setText(rentCar.getCarBrand().getName() + " " + rentCar.getModel());
        if (rentCar.getImages() == null) {
            return;
        }
        File carImageFile = new File(rentCar.getImages());
        Image carImage = new Image(carImageFile.toURI().toString());
        carImageView.setImage(carImage);
    }
}
