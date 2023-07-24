package com.example.rent_a_car_oop2;

public class CarsDataModel {
    private int carId;
    private String carBrand, carModel, carClass, carCategory, carCharacteristics, carSmoker;
    private byte carPhoto;
    public CarsDataModel(){
        this.carId = 0;
        this.carBrand = "";
        this.carModel = "";
        this.carClass = "";
        this.carCategory = "";
        this.carCharacteristics = "";
        this.carPhoto = 0;
        this.carSmoker = "";
    }
    public CarsDataModel(int carId, String carBrand, String carModel, String carClass, String carCategory,
                         String carCharacteristics, byte carPhoto, String carSmoker){
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carClass = carClass;
        this.carCategory = carCategory;
        this.carCharacteristics = carCharacteristics;
        this.carPhoto = carPhoto;
        this.carSmoker = carSmoker;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getCarCharacteristics() {
        return carCharacteristics;
    }

    public void setCarCharacteristics(String carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }

    public String getCarSmoker() {
        return carSmoker;
    }

    public void setCarSmoker(String carSmoker) {
        this.carSmoker = carSmoker;
    }

    public byte getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(byte carPhoto) {
        this.carPhoto = carPhoto;
    }
}
