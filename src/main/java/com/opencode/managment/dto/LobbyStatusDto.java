package com.opencode.managment.dto;

public class LobbyStatusDto {
    private int status;

    public LobbyStatusDto(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
