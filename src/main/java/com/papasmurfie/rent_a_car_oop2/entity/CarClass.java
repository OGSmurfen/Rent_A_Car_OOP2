package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "car_class", schema = "public", catalog = "postgres")
public class CarClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public CarClass() {
    }
    public CarClass(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarClass carClass = (CarClass) o;
        return id == carClass.id && Objects.equals(name, carClass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
