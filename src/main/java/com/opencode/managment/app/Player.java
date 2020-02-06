package com.opencode.managment.app;

import com.opencode.managment.dto.PlayerDTO;
import com.opencode.managment.entity.User;

public class Player {
    private User user;
    private String userName;
    private int numberInLobby;
    private String isCrownPlayer;

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
}
