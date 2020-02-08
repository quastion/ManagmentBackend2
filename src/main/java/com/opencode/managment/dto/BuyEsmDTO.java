package com.opencode.managment.dto;

public class BuyEsmDTO {
    private String userName;
    private int numberEsm;
    private int costEsm;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberEsm() {
        return numberEsm;
    }

    public void setNumberEsm(int numberEsm) {
        this.numberEsm = numberEsm;
    }

    public int getCostEsm() {
        return costEsm;
    }

    public void setCostEsm(int costEsm) {
        this.costEsm = costEsm;
    }
}
