package com.example.rent_a_car_oop2;

public class OperatorDataModel {
    private int operatorId;
    private String operatorNames;
    private String operatorJob;

    public OperatorDataModel(int operatorId, String operatorNames, String operatorJob) {
        this.operatorId = operatorId;
        this.operatorNames = operatorNames;
        this.operatorJob = operatorJob;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorNames() {
        return operatorNames;
    }

    public void setOperatorNames(String operatorNames) {
        this.operatorNames = operatorNames;
    }

    public String getOperatorJob() {
        return operatorJob;
    }

    public void setOperatorJob(String operatorJob) {
        this.operatorJob = operatorJob;
    }
}
