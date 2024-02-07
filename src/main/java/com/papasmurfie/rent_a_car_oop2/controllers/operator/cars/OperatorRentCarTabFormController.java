package com.papasmurfie.rent_a_car_oop2.controllers.operator.cars;

import com.papasmurfie.rent_a_car_oop2.Main;
import com.papasmurfie.rent_a_car_oop2.controllers.login.AuthenticationController;
import com.papasmurfie.rent_a_car_oop2.controllers.login.LoginFormController;
import com.papasmurfie.rent_a_car_oop2.entity.*;
import com.papasmurfie.rent_a_car_oop2.repository.impl.CarRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.repository.impl.RentsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.repository.impl.UserRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.AuthenticationService;
import com.papasmurfie.rent_a_car_oop2.service.CarService;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.papasmurfie.rent_a_car_oop2.controllers.operator.cars.OperatorRentCarDialogFormConfiguration.RETURN;

public class OperatorRentCarTabFormController {
    @FXML
    private Button DeleteCarButton;
    @FXML
    private Button RentCarButton;
    @FXML
    private TextField insertCarModelTextField;
    @FXML
    private TextField carCharacteristicsTextField;
    @FXML
    private CheckBox smokerCheckButton;
    @FXML
    private TableColumn isRentedColumn;
    @FXML
    private CheckBox availableCarsCheckBox;
    @FXML
    private DatePicker dateRentedField;
    @FXML
    private TextField descriptionTakeField;
    @FXML
    private TextField kmTextField;
    @FXML
    private TextField descriptionReturnField;
    @FXML
    private DatePicker dateReturnedField;

    @FXML
    private ComboBox SelectClassComboBox;
    @FXML
    private ComboBox SelectCategoryComboBox;
    @FXML
    private ComboBox<String> SelectBrandComboBox;

    @FXML
    private TableView<Cars> CarsTableView;
    @FXML
    private TableColumn<Cars, Integer> CarIdColumn;
    @FXML
    private TableColumn<Cars, String> CarBrandColumn;
    @FXML
    private TableColumn<Cars, String> CarModelColumn;
    @FXML
    private TableColumn<Cars, String> CarClassColumn;
    @FXML
    private TableColumn<Cars, String> CarCategoryColumn;
    @FXML
    private TableColumn<Cars, String> CarCharacteristicsColumn;
    @FXML
    private TableColumn<Cars, Boolean> CarSmokerColumn;
    @FXML
    private Button AddCarButton;

// TODO: Populate the other combo boxes.

    private final CarController carController = new CarController(new CarService(new CarRepositoryImpl()));
    private ObservableList<Cars> carsDataList;
    private ObservableList<CarBrand> carBrandsDataList;

    public void initialize() {
        populateTable();
        populateComboBoxes();

        CarsTableView.setOnMouseClicked(event -> {
                 if (event.getClickCount() == 2) { // Check for a double click
                Cars selectedCar = CarsTableView.getSelectionModel().getSelectedItem();
                if (selectedCar != null) {
                    OperatorRentCarDialogFormController operatorRentCarDialogFormController =
                            new OperatorRentCarDialogFormController(selectedCar,
                                    this,
                                    dialogConfiguration(selectedCar));
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("RentCar.fxml"));
                    loader.setController(operatorRentCarDialogFormController);
                    try {
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private OperatorRentCarDialogFormConfiguration dialogConfiguration(Cars selectedCar) {
        if (selectedCar.isIsrented()) {
            return OperatorRentCarDialogFormConfiguration.RETURN;
        } else {
            return OperatorRentCarDialogFormConfiguration.RENT;
        }
    }

    private void populateComboBoxes() {
        populateBrandCB();
        populateClassCB();
        populateCategoryCB();
    }

    private void populateCategoryCB() {
        List<CarCategory> carCategories = carController.findAllCategories();
        ObservableList<CarCategory> carCategoryDataList = FXCollections.observableArrayList(carCategories);
        List<String> categoryNames = carCategories.stream()
                .map(CarCategory::getName)
                .collect(Collectors.toList());
        SelectCategoryComboBox.setItems(FXCollections.observableArrayList(categoryNames));
    }

    private void populateClassCB() {
        List<CarClass> carClasses = carController.findAllClasses();
        ObservableList<CarClass> carClassesDataList = FXCollections.observableArrayList(carClasses);

        List<String> classNames = carClasses.stream()
                .map(CarClass::getName)
                .collect(Collectors.toList());
        SelectClassComboBox.setItems(FXCollections.observableArrayList(classNames));
    }

    private void populateBrandCB() {
        List<CarBrand> carBrands = carController.findAllBrands();
        carBrandsDataList = FXCollections.observableArrayList(carBrands);

        List<String> brandNames = carBrands.stream()
                .map(CarBrand::getName)
                .collect(Collectors.toList());
        SelectBrandComboBox.setItems(FXCollections.observableArrayList(brandNames));
    }

    public  void populateTable() {
        List<Cars> carsList = carController.findAll();
        if(carsDataList != null){carsDataList.clear();}
        carsDataList = FXCollections.observableArrayList(carsList);
        CarsTableView.setItems(carsDataList);
        setupColumns();
    }
    private  void populateTable(List<Cars> carsList) {
        //List<Cars> carsList = carController.findAll();
        if(carsDataList != null){carsDataList.clear();}
        carsDataList = FXCollections.observableArrayList(carsList);
        CarsTableView.setItems(carsDataList);
        setupColumns();
    }

    private void setupColumns() {
        CarIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        CarBrandColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarBrand().getName()));
        CarModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        CarClassColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarClass().getName()));
        CarCategoryColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarCategory().getName()));
        CarCharacteristicsColumn.setCellValueFactory(new PropertyValueFactory<>("characteristics"));
        CarSmokerColumn.setCellValueFactory(new PropertyValueFactory<>("smoker"));
        isRentedColumn.setCellValueFactory(new PropertyValueFactory<>("isrented"));
    }
    public void AddCarButtonOnAction() {
//if Insert TextField(s) empty -> Alert
        if (insertCarModelTextField.getText().isEmpty() || carCharacteristicsTextField.getText().isEmpty()
        || SelectCategoryComboBox.getValue()==null || SelectClassComboBox.getValue()==null
        || SelectBrandComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert all data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        boolean smoker;
        if(smokerCheckButton.isSelected()){smoker = true;}else{smoker = false;}
        Cars car = new Cars(carController.findBrand(SelectBrandComboBox.getValue()) ,
                insertCarModelTextField.getText(),
               carController.findCarClass((String) SelectClassComboBox.getValue()),
                carController.findCarCategory((String) SelectCategoryComboBox.getValue()),
                carCharacteristicsTextField.getText(),
                "none",
                smoker,
                false);
        carController.addCar(car);
        carsDataList.add(car);


        smokerCheckButton.setSelected(false);
        SelectBrandComboBox.setValue(null);
        SelectCategoryComboBox.setValue(null);
        SelectClassComboBox.setValue(null);
        carCharacteristicsTextField.setText("");
        insertCarModelTextField.setText("");
    }

    public void DeleteCarButtonOnAction() {
        Cars selectedCar = (Cars) CarsTableView.getSelectionModel().getSelectedItem();
        if (selectedCar == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected row(s)?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            try {
                carController.deleteCar(selectedCar);
                carsDataList.remove(selectedCar);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot delete a car that has been rented.", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    public void onAvailableCarsCheckboxChecked(ActionEvent event) {
        if(availableCarsCheckBox.isSelected()) {
            List<Cars> carsList = carController.findAvailableCars(!availableCarsCheckBox.isSelected());
            populateTable(carsList);
        }
        if(!availableCarsCheckBox.isSelected()){
            populateTable();
        }

    }
}
