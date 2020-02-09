package com.opencode.managment.dto;

public class LoanDTO {
    private String userName;
    private int loanValue;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(int loanValue) {
        this.loanValue = loanValue;
    }
}
