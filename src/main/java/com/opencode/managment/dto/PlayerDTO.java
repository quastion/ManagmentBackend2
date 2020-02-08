package com.opencode.managment.dto;

public class PlayerDTO {
    private String userName;
    private int numberInLobby;
    private String isCrownPlayer;
    private String player; //'имя игрока1',
    private String ava; // строка значения аватарок а1-а10
    private int money; // сколько денег у игрока
    private int superFactory; // сколько КРУТЫХ фабрик у игрока
    private int factory; // сколько обычных фабрик у игрока
    private int esm; // сколько ЕСМ у игрока
    private int egp; // сколько ЕГП у игрока
    private int time; // индивидуальный счетчик времени оствшегося на ход

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

    public String getIsCrownPlayer() {
        return isCrownPlayer;
    }

    public void setIsCrownPlayer(String isCrownPlayer) {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
