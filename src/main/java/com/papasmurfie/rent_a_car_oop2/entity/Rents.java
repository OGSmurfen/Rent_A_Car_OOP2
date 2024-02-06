package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Rents {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rent_id")
    private int rentId;
    @Basic
    @Column(name = "car_id")
    private int carId;
    @Basic
    @Column(name = "client_id")
    private int clientId;
    @Basic
    @Column(name = "description_protocol")
    private String descriptionProtocol;
    @Basic
    @Column(name = "return_description_protocol")
    private String returnDescriptionProtocol;
    @Basic
    @Column(name = "date_rented")
    private Date dateRented;
    @Basic
    @Column(name = "date_returned")
    private Date dateReturned;

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDescriptionProtocol() {
        return descriptionProtocol;
    }

    public void setDescriptionProtocol(String descriptionProtocol) {
        this.descriptionProtocol = descriptionProtocol;
    }

    public String getReturnDescriptionProtocol() {
        return returnDescriptionProtocol;
    }

    public void setReturnDescriptionProtocol(String returnDescriptionProtocol) {
        this.returnDescriptionProtocol = returnDescriptionProtocol;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rents rents = (Rents) o;
        return rentId == rents.rentId && carId == rents.carId && clientId == rents.clientId && Objects.equals(descriptionProtocol, rents.descriptionProtocol) && Objects.equals(returnDescriptionProtocol, rents.returnDescriptionProtocol) && Objects.equals(dateRented, rents.dateRented) && Objects.equals(dateReturned, rents.dateReturned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentId, carId, clientId, descriptionProtocol, returnDescriptionProtocol, dateRented, dateReturned);
    }
}
