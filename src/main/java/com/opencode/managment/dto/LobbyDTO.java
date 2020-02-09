package com.opencode.managment.dto;

import com.opencode.managment.app.Lobby;
import com.opencode.managment.app.Player;
import com.opencode.managment.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class LobbyDTO {
    private String lobbyName;
    private int countOfPlayer;
    private String creator;
    private List<User> users;

    public LobbyDTO() {
    }

    public LobbyDTO(Lobby lobby) {
        if(lobby == null) return;

        lobbyName = lobby.getLobbyName();
        countOfPlayer = lobby.getCountOfPlayer();
        creator = lobby.getCreator();
        users = lobby.getPlayers().stream().map((Player player) -> player.getUser()).collect(Collectors.toList());
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public int getCountOfPlayer() {
        return countOfPlayer;
    }

    public void setCountOfPlayer(int countOfPlayer) {
        this.countOfPlayer = countOfPlayer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
