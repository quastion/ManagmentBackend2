package com.opencode.managment.dto;

public class ProductConversionIntentionDTO {
    private String userName;
    private int numberOfEsmToEgp;
    private int sumOfMoney;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfEsmToEgp() {
        return numberOfEsmToEgp;
    }

    public void setNumberOfEsmToEgp(int numberOfEsmToEgp) {
        this.numberOfEsmToEgp = numberOfEsmToEgp;
    }

    public int getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(int sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }
}
