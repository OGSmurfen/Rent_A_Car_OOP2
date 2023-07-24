package com.example.rent_a_car_oop2;

public class ClientDataModel {
    private int clientId;
    private String clientName;
    public ClientDataModel(){
    this.clientId = 0;
    this.clientName = "";
    }
    public ClientDataModel(int clientId, String clientName){
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
