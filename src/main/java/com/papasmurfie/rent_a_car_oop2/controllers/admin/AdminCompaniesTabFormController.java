package com.papasmurfie.rent_a_car_oop2.controllers.admin;

import com.papasmurfie.rent_a_car_oop2.entity.Company;
import com.papasmurfie.rent_a_car_oop2.repository.impl.CompanyRepositoryImpl;
import com.papasmurfie.rent_a_car_oop2.service.CompanyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminCompaniesTabFormController implements Initializable {

    @FXML
    private ImageView RefreshCompaniesImageView;
    @FXML
    private TableView<Company> CompaniesTableView;
    @FXML
    private TableColumn<Company, Integer> CompanyIdColumn;
    @FXML
    private TableColumn<Company, String> CompanyNameColumn;
    @FXML
    private TextField InsertCompanyNameField;

    private final CompaniesController companiesController = new CompaniesController(new CompanyService(new CompanyRepositoryImpl()));
    private ObservableList<Company> companyDataList;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCompanies();
    }

    private void loadCompanies() {
        List<Company> companyList = companiesController.getCompanies();
        companyDataList = FXCollections.observableArrayList(companyList);

        CompaniesTableView.setItems(companyDataList);
        CompanyIdColumn.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
    }

    public void DeleteCompanyButtonOnAction() {
        // Select a row from tableview
        Company selectedItem = CompaniesTableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            // alert for no row selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row from TableView", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected row(s)?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            // Delete the company from the database
            companiesController.deleteCompany(selectedItem);

            // Update the ObservableList (and consequently, the TableView)
            companyDataList.remove(selectedItem);
        }
    }

    public void AddCompanyButtonOnAction() {
        String companyName = InsertCompanyNameField.getText();
        if(companyName.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data to be added!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Company company = new Company(companyName);
        companiesController.addCompany(company);
        companyDataList.add(company);
    }
}
