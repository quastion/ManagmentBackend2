package com.opencode.managment.dto;

public class PlayerDTO {
    private String userName;
    private int numberInLobby;
    private boolean isCrownPlayer;
    private String player; //'имя игрока1',
    private String ava; // строка значения аватарок а1-а10
    private int money; // сколько денег у игрока
    private int superFactory; // сколько КРУТЫХ фабрик у игрока
    private int factory; // сколько обычных фабрик у игрока
    private int esm; // сколько ЕСМ у игрока
    private int egp; // сколько ЕГП у игрока
    private int time; // индивидуальный счетчик времени оствшегося на ход
    private int availableLoansCount;
    private boolean isLoan;
    private int outstandingLoan; //остаток погашения ссуды
    private int loanRepaymentTime; //время до погашения ссуды

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberInLobby() {
        return numberInLobby;
    }

    public void setNumberInLobby(int numberInLobby) {
        this.numberInLobby = numberInLobby;
    }

    public boolean getIsCrownPlayer() {
        return isCrownPlayer;
    }

    public void setIsCrownPlayer(boolean isCrownPlayer) {
        this.isCrownPlayer = isCrownPlayer;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSuperFactory() {
        return superFactory;
    }

    public void setSuperFactory(int superFactory) {
        this.superFactory = superFactory;
    }

    public int getFactory() {
        return factory;
    }

    public void setFactory(int factory) {
        this.factory = factory;
    }

    public int getEsm() {
        return esm;
    }

    public void setEsm(int esm) {
        this.esm = esm;
    }

    public int getEgp() {
        return egp;
    }

    public void setEgp(int egp) {
        this.egp = egp;
    }

    public boolean getIsLoan() {
        return isLoan;
    }

    public void setIsLoan(boolean loan) {
        isLoan = loan;
    }

    public int getAvailableLoansCount() {
        return availableLoansCount;
    }

    public void setAvailableLoansCount(int availableLoansCount) {
        this.availableLoansCount = availableLoansCount;
    }

    public int getOutstandingLoan() {
        return outstandingLoan;
    }

    public void setOutstandingLoan(int outstandingLoan) {
        this.outstandingLoan = outstandingLoan;
    }

    public int getLoanRepaymentTime() {
        return loanRepaymentTime;
    }

    public void setLoanRepaymentTime(int loanRepaymentTime) {
        this.loanRepaymentTime = loanRepaymentTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
