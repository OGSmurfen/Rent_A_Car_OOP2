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
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Cars car;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Clients client;
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
    @Basic
    @Column(name = "kilometers_driven")
    private int kmDriven;

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Clients getClient() {
        return client;
    }

    public void setClientId(Clients client) {
        this.client = client;
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
    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rents rents = (Rents) o;
        return rentId == rents.rentId && car == rents.car && client == rents.client && Objects.equals(descriptionProtocol, rents.descriptionProtocol) && Objects.equals(returnDescriptionProtocol, rents.returnDescriptionProtocol) && Objects.equals(dateRented, rents.dateRented) && Objects.equals(dateReturned, rents.dateReturned) && kmDriven == rents.kmDriven;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentId, car, client, descriptionProtocol, returnDescriptionProtocol, dateRented, dateReturned, kmDriven);
    }
}
