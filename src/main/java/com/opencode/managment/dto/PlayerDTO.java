package com.opencode.managment.dto;

public class PlayerDTO {
    private String userName;
    private int numberInLobby;
    private String isCrownPlayer;

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
}
