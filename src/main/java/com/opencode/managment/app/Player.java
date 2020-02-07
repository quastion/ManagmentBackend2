package com.opencode.managment.app;

import com.opencode.managment.dto.PlayerDTO;
import com.opencode.managment.entity.User;

public class Player {
    private User user;
    private String userName;
    private int numberInLobby;
    private String isCrownPlayer;

    //Фабрики
    private int standardFactoriesCount = 2;
    private int automatedFactoriesCount = 0;
    private int esm = 4;
    private int egp = 2;
    private int money = 10000;


    public Player(PlayerDTO playerDTO) {
        userName = playerDTO.getUserName();
        numberInLobby = playerDTO.getNumberInLobby();
        isCrownPlayer = playerDTO.getIsCrownPlayer();
    }

    public Player(User user) {
        this.user = user;
        userName = user.getUserName();
    }

    public User getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberInLobby() {
        return numberInLobby;
    }

    public String getIsCrownPlayer() {
        return isCrownPlayer;
    }

    public int getStandardFactoriesCount() {
        return standardFactoriesCount;
    }

    public void setStandardFactoriesCount(int standardFactoriesCount) {
        this.standardFactoriesCount = standardFactoriesCount;
    }

    public int getAutomatedFactoriesCount() {
        return automatedFactoriesCount;
    }

    public void setAutomatedFactoriesCount(int automatedFactoriesCount) {
        this.automatedFactoriesCount = automatedFactoriesCount;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void changeMoney(int value){
        money+=value;
    }

    public void changeEsm(int value){
        esm+=value;
    }

    public void changeEgp(int value){
        egp+=value;
    }
}
