package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Cardamageprices {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "car_class_id")
    private int carClassId;
    @Basic
    @Column(name = "damage_condition_id")
    private int damageConditionId;
    @Basic
    @Column(name = "price")
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(int carClassId) {
        this.carClassId = carClassId;
    }

    public int getDamageConditionId() {
        return damageConditionId;
    }

    public void setDamageConditionId(int damageConditionId) {
        this.damageConditionId = damageConditionId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardamageprices that = (Cardamageprices) o;
        return id == that.id && carClassId == that.carClassId && damageConditionId == that.damageConditionId && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carClassId, damageConditionId, price);
    }
}
