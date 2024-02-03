package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id && Objects.equals(type, roles.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
