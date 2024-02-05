package com.papasmurfie.rent_a_car_oop2.controllers.operator;

import com.papasmurfie.rent_a_car_oop2.entity.CarBrand;
import com.papasmurfie.rent_a_car_oop2.entity.CarCategory;
import com.papasmurfie.rent_a_car_oop2.entity.CarClass;
import com.papasmurfie.rent_a_car_oop2.entity.Cars;
import com.papasmurfie.rent_a_car_oop2.repository.impl.CarRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.CarService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OperatorRentCarTabFormController {
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
        List<CarBrand> carBrands = carController.findAllBrands();
        carBrandsDataList = FXCollections.observableArrayList(carBrands);

        List<String> brandNames = carBrands.stream()
                .map(CarBrand::getName)
                .collect(Collectors.toList());
        SelectBrandComboBox.setItems(FXCollections.observableArrayList(brandNames));
    }
    private  void populateTable() {
        List<Cars> carsList = carController.findAll();
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
    }
    public void AddCarButtonOnAction() {

    }

    public void DeleteCarButtonOnAction() {

    }

    public void RentCarButtonOnAction() {

    }
}
