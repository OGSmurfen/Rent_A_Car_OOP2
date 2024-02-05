package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cars {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;
    @Basic
    @Column(name = "model")
    private String model;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private CarClass carClass;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CarCategory carCategory;
    @Basic
    @Column(name = "characteristics")
    private String characteristics;
    @Basic
    @Column(name = "images")
    private String images;
    @Basic
    @Column(name = "smoker")
    private boolean smoker;

    public Cars() {

    }

    public Cars(CarBrand carBrand, String model, CarClass carClass,
                CarCategory carCategory, String characteristics, String images, boolean smoker) {
        this.carBrand = carBrand;
        this.model = model;
        this.carClass = carClass;
        this.carCategory = carCategory;
        this.characteristics = characteristics;
        this.images = images;
        this.smoker = smoker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return id == cars.id && smoker == cars.smoker && Objects.equals(carBrand, cars.carBrand) && Objects.equals(model, cars.model) && Objects.equals(carClass, cars.carClass) && Objects.equals(carCategory, cars.carCategory) && Objects.equals(characteristics, cars.characteristics) && Objects.equals(images, cars.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carBrand, model, carClass, carCategory, characteristics, images, smoker);
    }
}
