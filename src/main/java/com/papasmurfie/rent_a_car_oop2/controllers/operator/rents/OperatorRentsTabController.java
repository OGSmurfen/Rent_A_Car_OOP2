package com.papasmurfie.rent_a_car_oop2.controllers.operator.rents;

import com.papasmurfie.rent_a_car_oop2.controllers.operator.cars.CarController;
import com.papasmurfie.rent_a_car_oop2.entity.*;
import com.papasmurfie.rent_a_car_oop2.repository.impl.CarRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.repository.impl.RentsRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.CarService;
import com.papasmurfie.rent_a_car_oop2.service.RentsService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OperatorRentsTabController {
    @FXML
    private DatePicker dateBeginPicker;
    @FXML
    private DatePicker dateEndPicker;
    @FXML
    private TableColumn rentedDateColumn;
    @FXML
    private TableColumn<Rents, LocalDate> returnedDateColumn;
    @FXML
    private TableColumn kmColumn;
    @FXML
    private TableColumn descrTakeColumn;
    @FXML
    private TableColumn descriptionReturnColumn;
    @FXML
    private TableView rentsTableView;
    @FXML
    private TableColumn rentIdColumn;
    @FXML
    private TableColumn<Rents, String> clientNameColumn;
    @FXML
    private CheckBox carClassCheckbox;
    @FXML
    private ComboBox carClassComboBox;
    @FXML
    private ComboBox carModelComboBox;
    @FXML
    private CheckBox carCategoryCheckbox;
    @FXML
    private ComboBox carCategoryComboBox;
    @FXML
    private TableColumn<Rents, String> CarBrandColumn;
    @FXML
    private TableColumn<Rents, String> CarModelColumn;
    @FXML
    private TableColumn<Rents, String> CarClassColumn;
    @FXML
    private TableColumn<Rents, String> CarCategoryColumn;
    @FXML
    private TableColumn<Rents, String> CarCharacteristicsColumn;
    @FXML
    private TableColumn<Rents, Boolean> CarSmokerColumn;
    @FXML
    private TableColumn<Rents, Boolean> isRentedColumn;

    private final CarController carController = new CarController(new CarService(new CarRepositoryImpl()));
    private final RentController rentController = new RentController(new RentsService(new RentsRepositoryImpl()));
    private ObservableList<Cars> carsDataList;
    private ObservableList<Rents> rentsDataList;
    private ObservableList<CarBrand> carBrandsDataList;
    private List<Rents> rentsList;
    public void initialize() {
        populateTable();
        populateComboBoxes();
    }

    private void populateComboBoxes() {
        //populateBrandCB();
        populateClassCB();
        populateCategoryCB();
    }

    private void populateCategoryCB() {
        List<CarCategory> carCategories = carController.findAllCategories();
        ObservableList<CarCategory> carCategoryDataList = FXCollections.observableArrayList(carCategories);
        List<String> categoryNames = carCategories.stream()
                .map(CarCategory::getName)
                .collect(Collectors.toList());
        carCategoryComboBox.setItems(FXCollections.observableArrayList(categoryNames));
    }

    private void populateClassCB() {
        List<CarClass> carClasses = carController.findAllClasses();
        ObservableList<CarClass> carClassesDataList = FXCollections.observableArrayList(carClasses);

        List<String> classNames = carClasses.stream()
                .map(CarClass::getName)
                .collect(Collectors.toList());
        carClassComboBox.setItems(FXCollections.observableArrayList(classNames));
    }

    private void populateBrandCB() {
        List<CarBrand> carBrands = carController.findAllBrands();
        carBrandsDataList = FXCollections.observableArrayList(carBrands);

        List<String> brandNames = carBrands.stream()
                .map(CarBrand::getName)
                .collect(Collectors.toList());
        carModelComboBox.setItems(FXCollections.observableArrayList(brandNames));
    }

    private  void populateTable() {
        rentsList = rentController.findAll();
        if(rentsDataList != null){rentsDataList.clear();}
        rentsDataList = FXCollections.observableArrayList(rentsList);
        rentsTableView.setItems(rentsDataList);
        setupColumns();
    }
    private  void populateTable(List<Rents> rentsList) {
        //List<Cars> carsList = carController.findAll();
        if(rentsDataList != null){rentsDataList.clear();}
        rentsDataList = FXCollections.observableArrayList(rentsList);
        rentsTableView.setItems(rentsDataList);
        setupColumns();
    }

    private void setupColumns() {
        rentIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        clientNameColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getClient().getClientName()));
        CarBrandColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCar().getCarBrand().getName()));
        CarModelColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCar().getModel()));
        CarClassColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCar().getCarClass().getName()));
        CarCategoryColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCar().getCarCategory().getName()));
        CarCharacteristicsColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCar().getCharacteristics()));
        CarSmokerColumn.setCellValueFactory(cd -> new SimpleBooleanProperty(cd.getValue().getCar().isSmoker()));
        isRentedColumn.setCellValueFactory(cd -> new SimpleBooleanProperty(cd.getValue().getCar().isIsrented()));
        rentedDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRented"));
        returnedDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));
        kmColumn.setCellValueFactory(new PropertyValueFactory<>("kmDriven"));
        descrTakeColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionProtocol"));
        descriptionReturnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDescriptionProtocol"));
    }

    public void onCarClassCheckboxChecked(ActionEvent actionEvent) {
        if(carClassComboBox.getValue() == null)
            return;

        if(carClassCheckbox.isSelected()) {
            String str = "Class";
            List<Rents> rentsList1 = rentController.findBy(str, carClassComboBox.getValue().toString());
            rentsList = rentsList1;
            populateTable(rentsList1);

        }
        if(!carClassCheckbox.isSelected()){
            populateTable();
        }

    }

    public void onCarCategoryCheckboxChecked(ActionEvent actionEvent) {
        if(carCategoryComboBox.getValue() == null)
            return;

        if(carCategoryCheckbox.isSelected()) {
            String str = "Category";
            List<Rents> rentsList1 = rentController.findBy(str, carCategoryComboBox.getValue().toString());
            rentsList = rentsList1;
            populateTable(rentsList1);

        }
        if(!carCategoryCheckbox.isSelected()){
            populateTable();
        }
    }

    public void onCarModelCheckboxChecked(ActionEvent actionEvent) {

    }

    public void onFindBtnPressed(ActionEvent event) {
        if(dateBeginPicker.getValue() == null || dateEndPicker.getValue() == null)
            return;

        List<Rents>rentsList1 = rentController.findInDateDiapazon(Date.valueOf(dateBeginPicker.getValue()), Date.valueOf(dateEndPicker.getValue()));
        rentsList = rentsList1;
        populateTable(rentsList1);
    }

    public void onResetBtnPressed(ActionEvent event) {
        populateTable();
    }
}
