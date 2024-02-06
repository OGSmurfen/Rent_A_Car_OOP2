package com.papasmurfie.rent_a_car_oop2.controllers.operator.rent_cars;

import com.papasmurfie.rent_a_car_oop2.entity.*;
import com.papasmurfie.rent_a_car_oop2.repository.impl.CarRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.CarService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OperatorRentCarTabFormController {
    public Button DeleteCarButton;
    public Button RentCarButton;
    public TextField insertCarModelTextField;
    public TextField carCharacteristicsTextField;
    public CheckBox smokerCheckButton;
    public TableColumn isRentedColumn;
    public CheckBox availableCarsCheckBox;
    @FXML
    private ComboBox SelectClassComboBox;
    @FXML
    private ComboBox SelectCategoryComboBox;
    @FXML
    private ComboBox<String> SelectBrandComboBox;
    @FXML
    public TableView<Cars> CarsTableView;
    public TableColumn<Cars, Integer> CarIdColumn;
    public TableColumn<Cars, String> CarBrandColumn;
    public TableColumn<Cars, String> CarModelColumn;
    public TableColumn<Cars, String> CarClassColumn;
    public TableColumn<Cars, String> CarCategoryColumn;
    public TableColumn<Cars, String> CarCharacteristicsColumn;
    public TableColumn<Cars, Boolean> CarSmokerColumn;
    public Button AddCarButton;

// TODO: Populate the other combo boxes.

    private final CarController carController = new CarController(new CarService(new CarRepositoryImpl()));
    private ObservableList<Cars> carsDataList;
    private ObservableList<CarBrand> carBrandsDataList;

    public void initialize() {
        populateTable();
        populateComboBoxes();
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

    private  void populateTable() {
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
            carController.deleteCar(selectedCar);
            carsDataList.remove(selectedCar);
        }
    }

    public void RentCarButtonOnAction() {
        Cars selectedCar = (Cars) CarsTableView.getSelectionModel().getSelectedItem();
        int selectedRow = CarsTableView.getSelectionModel().getSelectedIndex();
        if(selectedCar == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        selectedCar.setIsrented(!selectedCar.isIsrented());
        carController.updateCar(selectedCar);

        populateTable();
        //select same row
        CarsTableView.getSelectionModel().select(selectedRow);
        CarsTableView.getFocusModel().focus(selectedRow);
    }

    public void onAvailableCarsCheckboxChecked(ActionEvent event) {
        if(availableCarsCheckBox.isSelected()) {
            List<Cars> carsList = carController.findAvailableCars(!availableCarsCheckBox.isSelected());
            populateTable(carsList);
        }
        if(!availableCarsCheckBox.isSelected()){
            List<Cars> carsList = carController.findAll();
            populateTable(carsList);
        }

    }
}
