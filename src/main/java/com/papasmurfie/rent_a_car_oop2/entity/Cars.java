package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Cars {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private int carId;
    @Basic
    @Column(name = "car_brand")
    private String carBrand;
    @Basic
    @Column(name = "car_model")
    private String carModel;
    @Basic
    @Column(name = "car_class")
    private String carClass;
    @Basic
    @Column(name = "car_category")
    private String carCategory;
    @Basic
    @Column(name = "car_characteristics")
    private String carCharacteristics;
    @Basic
    @Column(name = "car_photo")
    private byte[] carPhoto;
    @Basic
    @Column(name = "car_smoker")
    private String carSmoker;

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

    public byte[] getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(byte[] carPhoto) {
        this.carPhoto = carPhoto;
    }

    public String getCarSmoker() {
        return carSmoker;
    }

    public void setCarSmoker(String carSmoker) {
        this.carSmoker = carSmoker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return carId == cars.carId && Objects.equals(carBrand, cars.carBrand) && Objects.equals(carModel, cars.carModel) && Objects.equals(carClass, cars.carClass) && Objects.equals(carCategory, cars.carCategory) && Objects.equals(carCharacteristics, cars.carCharacteristics) && Arrays.equals(carPhoto, cars.carPhoto) && Objects.equals(carSmoker, cars.carSmoker);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(carId, carBrand, carModel, carClass, carCategory, carCharacteristics, carSmoker);
        result = 31 * result + Arrays.hashCode(carPhoto);
        return result;
    }
}
